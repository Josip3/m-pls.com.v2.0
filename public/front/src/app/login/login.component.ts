import {Component, ElementRef, OnInit, ViewChild} from "@angular/core";
import {Router} from "@angular/router";
import {LoginService} from "./login.service";
import {AppComponent} from "../app.component";
import {CookieService} from "angular2-cookie/services";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService, CookieService]
})
export class LoginComponent implements OnInit {

  lang: string;

  @ViewChild('inputChecked') inputCheckedF: ElementRef;

  constructor(private _route: Router, private _loginService: LoginService, private _cookieService: CookieService) {

    // this.lang = AppComponent.languageService.startLang;
    // AppComponent.languageService.lang$.subscribe(lang => {
    //   this.lang = lang;
    //   // alert(lang);
    // }, error => {
    //   console.error(error);
    // });

  }

  resetPassword() {
    event.preventDefault();
  }

  auth(login: HTMLInputElement, password: HTMLInputElement) {
    this._loginService.sendCredentials({username: login.value, password: password.value}).subscribe(next => {
      AppComponent.userDetailsService.tokenParseInLocalStorage(next.json());
      console.log(next.json());




      // this._loginService.getUser().subscribe(next => {
      //   AppComponent.userDetailsService.login(next);
      //   if (next.role == "ADMIN") {
      //     this._route.navigateByUrl("/admin");
      //   } else {
      //     this._route.navigateByUrl("/");
      //   }
      // }, error => {
      //   console.error(error);
      // });
      this._route.navigateByUrl("/");

    }, error => {
      console.error(error);
    });
  }

  ngOnInit() {
  }

}
