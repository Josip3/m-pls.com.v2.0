import {Component} from '@angular/core';
import {UserDetailsService} from "../environments/service/user-details.service";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  static userDetailsService: UserDetailsService = new UserDetailsService();
}
