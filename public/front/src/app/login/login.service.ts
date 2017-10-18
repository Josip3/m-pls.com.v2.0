import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions, RequestOptionsArgs} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Url} from "../../environments/config/url";
import {User} from "../../environments/model/user";
import "rxjs/add/operator/map";

@Injectable()
export class LoginService {

  constructor(private _http: Http) {
  }

  static tokenName: string = "access_token";

  login(name: string, password: string): Observable<Response> {
    let headers = new Headers({
      'authorization': 'Basic ' + btoa(name + ':' + password),
      'X-Requested-With': 'XMLHttpRequest'
    });

    let options = new RequestOptions({
      headers: headers
    });
    return this
      ._http
      .options(Url.url + "/", options)
      .catch((error) => Observable.throw(error));
  }


  getTest():Observable<Response>{
    return this._http.get(Url.url+"/test").catch((error) => Observable.throw(error));
  }

  //Sending credentials{username ,password for getting token}
  sendCredentials(model): Observable<Response> {
    let tokenUrl = Url.url + "/oauth/token";
    var data = 'username=' + encodeURIComponent(model.username) + '&password='
      + encodeURIComponent(model.password) + '&grant_type=password';
    return this._http.post(tokenUrl, data).catch((error) => Observable.throw(error));
  }

  // static getRequestOptionArgs(options?: RequestOptionsArgs, url?: string): RequestOptionsArgs {
  //   if (options == null) {
  //     options = new RequestOptions();
  //   }
  //   if (options.headers == null) {
  //     options.headers = new Headers();
  //   }
  //   if ((localStorage.getItem(this.tokenName) != null) && (localStorage.getItem(this.tokenName) != "")) {
  //     if (!options.headers.has("Authorization")) {
  //       options.headers.delete('Authorization');
  //       options.headers.append('Authorization', 'Bearer ' + localStorage.getItem(this.tokenName));
  //     }
  //     if (!options.headers.has("Content-Type"))
  //       options.headers.append('Content-Type', 'application/json');
  //   } else {
  //     options.headers.append('Authorization', 'Basic  Y2xpZW50YXBwOjEyMzQ1Ng==');
  //     if (!options.headers.has("Content-Type")) {
  //       options.headers.append('Content-Type', 'application/x-www-form-urlencoded');
  //       options.headers.append('Accept', 'application/json');
  //     }
  //   }
  //   return options;
  // }

  // //sends token to SERVERS PROTECTED RESOURCES if THIS ONE WILL PASS EVERYTHING IS WORKING
  // sendToken():Observable<any> {
  //   let userUrl = Url.url + "/restful/user/getCurrent";
  //   return this._http.get(userUrl);
  // }


  getUser(): Observable<User> {
    return this
      ._http
      .get(Url.url + "/user/controller/get-user").map(res => res.json())
      .catch((error) => Observable.throw(error));
  }


}
