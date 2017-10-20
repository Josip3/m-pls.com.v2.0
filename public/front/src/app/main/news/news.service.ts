import {Injectable} from '@angular/core';
import {Http, RequestOptions, Response, Headers} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Url} from "../../../environments/config/url";

@Injectable()
export class NewsService {
  constructor(private _http: Http) {
  }


  addFile(form: HTMLFormElement): Observable<Response> {
    let header = new Headers();
    header.append("Multipart", "Multipart");
    header.append("enctype", "multipart/form-data");
    // header.append('Content-Type','application/x-www-form-urlencoded; charset=UTF-8');

    let options = new RequestOptions();
    options.headers = header;
    let body = new FormData(form);
    return this._http.post(Url.url + "/add-file", body, options).catch(error => Observable.throw(error));
  }

  getAll(): Observable<string[]> {

    return this._http.get(Url.url + "/getAllImaget").map(res => res.json()).catch(error => Observable.throw(error));
  }

  deletePhoto(id: string): Observable<Response> {
    return this._http.delete(Url.url + "/restful/google-drive/delete/" + id).catch(error => Observable.throw(error));
  }

}
