import {Subject} from "rxjs/Subject";
import {CookieService} from "angular2-cookie/services";
import {isNullOrUndefined} from "util";
import {AppComponent} from "../../app/app.component";
import {CookieOptions} from "angular2-cookie/core";
import {User} from "../model/User";

export class UserDetailsService {


  public user: User = new User();
  private _user = new Subject<User>();
  user$ = this._user.asObservable();

  public isAuth: boolean = false;
  private _isAuth = new Subject<boolean>();
  isAuth$ = this._isAuth.asObservable();

  constructor(private _cookieService: CookieService = new CookieService()) {

  }


  public login(user: User) {
    this.user = user;
    this._user.next(this.user);
    this.isAuth = true;
    this._isAuth.next(this.isAuth);
  }

  public logout() {
    this.user = new User();
    this.user.role = "no_access_token";
    this._user.next(this.user);
    this.isAuth = false;
    this._isAuth.next(this.isAuth);
    this._cookieService.remove("access_token");
  }

  checkAuth(): boolean {
    return (!isNullOrUndefined(this._cookieService.get("access_token")) && !(this._cookieService.get("access_token") == "")) && !(AppComponent.userDetailsService.user.role == "no_access_token");
  }

  tokenParseInLocalStorage(data: any) {
    let date = new Date();
    date.setSeconds(data.expires_in);
    let conf = new CookieOptions({expires: date});
    this._cookieService.put("access_token", data.access_token, conf);
    this._cookieService.put("token_type", data.token_type, conf);
    this._cookieService.put("expires_in", new Date().setSeconds(data.expires_in) + "", conf);
    this._cookieService.put("scope", data.scope, conf);
    this._cookieService.put("jti", data.jti, conf);
    this._cookieService.put("refresh_token", data.refresh_token, conf);
  }

  updateTokenParseInLocalStorage() {
    let date = new Date();
    date.setSeconds(parseInt(this._cookieService.get("expires_in")));
    let conf = new CookieOptions({expires: date});
    this._cookieService.put("access_token", this._cookieService.get("access_token"), conf);
    this._cookieService.put("token_type", this._cookieService.get("token_type"), conf);
    this._cookieService.put("expires_in", new Date().setSeconds(parseInt(this._cookieService.get("expires_in"))) + "", conf);
    this._cookieService.put("scope", this._cookieService.get("scope"), conf);
    this._cookieService.put("jti", this._cookieService.get("jti"), conf);
    this._cookieService.put("refresh_token", this._cookieService.get("refresh_token"), conf);

  }


}
