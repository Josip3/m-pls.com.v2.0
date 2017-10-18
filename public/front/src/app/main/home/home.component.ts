import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../login/login.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [LoginService]
})
export class HomeComponent implements OnInit {

  constructor(private _loginService: LoginService) {
  }

  test() {
    this._loginService.getTest().subscribe(next => {
      console.log(next);
    }, error => {
      console.error(error);
    })
  }

  ngOnInit() {
  }

}
