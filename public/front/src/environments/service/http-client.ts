import {Injectable} from "@angular/core";
import {ConnectionBackend, Headers, Http, Request, RequestOptions, RequestOptionsArgs, Response} from "@angular/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {AppComponent} from "../../app/app.component";
import {CookieService} from "angular2-cookie/core";
import "rxjs/add/observable/empty";
import "rxjs/add/operator/catch";

@Injectable()
export class HttpClient extends Http {

  private tokenName: string = "access_token";
  private _cookieService: CookieService = new CookieService();

  constructor(backend: ConnectionBackend, defaultOptions: RequestOptions, private _router: Router) {
    super(backend, defaultOptions);
  }

  request(url: string | Request, options?: RequestOptionsArgs): Observable<Response> {
    return this.intercept(super.request(url, options));
  }


  get(url: string, options?: RequestOptionsArgs): Observable<Response> {
    options = this.getRequestOptionArgs(options);
    options.method = "GET";
    return this.intercept(super.get(url, options));
  }

  post(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
    options = this.getRequestOptionArgs(options, url);
    options.method = "POST";
    return this.intercept(super.post(url, body, options));
  }

  put(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
    options = this.getRequestOptionArgs(options);
    options.method = "PUT";
    return this.intercept(super.put(url, body, options));
  }

  delete(url: string, options?: RequestOptionsArgs): Observable<Response> {
    options = this.getRequestOptionArgs(options);
    options.method = "DELETE";
    return this.intercept(super.delete(url, options));
  }

  getRequestOptionArgs(options?: RequestOptionsArgs, url?: string): RequestOptionsArgs {
    if (options == null) {
      options = new RequestOptions();
    }
    if (options.headers == null) {
      options.headers = new Headers();
    }
    if ((this._cookieService.get(this.tokenName) != null) && (this._cookieService.get(this.tokenName) != "")) {
      // alert((this._cookieService.get(this.tokenName) != null) && (this._cookieService.get(this.tokenName) != ""));
      if (AppComponent.userDetailsService.checkAuth()) {
        AppComponent.userDetailsService.updateTokenParseInLocalStorage();
      }

      if (options.headers.has("NotAuthorization")) {
        options.headers.delete('NotAuthorization');
      } else {
        if (!options.headers.has("Authorization")) {
          options.headers.delete('Authorization');
          options.headers.append('Authorization', 'Bearer ' + this._cookieService.get(this.tokenName));
        }
      }
      options.headers.append('Accept', 'application/json');
    } else {
      if (options.headers.has("NotAuthorization")) {
        options.headers.delete('NotAuthorization');
      } else {
        options.headers.append('Authorization', 'Basic  Y2xpZW50YXBwOjEyMzQ1Ng==');
      }
      if (!options.headers.has("Content-Type")) {
        options.headers.append('Content-Type', 'application/x-www-form-urlencoded');
        options.headers.append('Accept', 'application/json');
      }
    }
    return options;
  }

  intercept(observable: Observable<Response>): Observable<Response> {
    return observable.catch((err, source) => {
      if (err.status == 401) {
        this._router.navigate(['/login']);
        this._cookieService.remove("access_token");
        return Observable.empty();
      } else {
        return Observable.throw(err);
      }
    });

  }
}
