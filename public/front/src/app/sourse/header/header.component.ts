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

  menu(ul: HTMLUListElement, a: HTMLAnchorElement) {
    event.preventDefault();

    if (ul.style.display == "none") {
      a.style.color="black";
      ul.style.display = "block";
    } else {
        a.style.color="white";
      ul.style.display = "none";
    }
  }

}
