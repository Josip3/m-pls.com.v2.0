import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

  menu(ul: HTMLUListElement) {
    event.preventDefault();
    if (ul.style.display == "none") {
      ul.style.display = "block";
    } else {
      ul.style.display = "none";
    }
  }

}
