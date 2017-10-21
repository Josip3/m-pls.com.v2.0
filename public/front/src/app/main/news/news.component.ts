import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {NewsService} from "./news.service";
import {Url} from "../../../environments/config/url";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css'],
  providers: [NewsService]
})
export class NewsComponent implements OnInit {

  path: string[] = [];
  url: string;
  zoom: number = 1;




  lat: number = 49.8390024;
  lng: number = 24.0233644;

  constructor(private _newsService: NewsService) {
    this.url = Url.url;
    this._newsService.getAll().subscribe(next => {
      this.path = next;
    }, error => {
      console.log(error);
    });

  }


  delete(id: string) {
    event.preventDefault();

    this._newsService.deletePhoto(id).subscribe(next => {
      console.log(next);
      this._newsService.getAll().subscribe(next => {
        this.path = next;
      }, error => {
        console.log(error);
      });
    }, error => {
      console.error(error);
    });
  }

  addFile(form: HTMLFormElement) {
    event.preventDefault();
    this._newsService.addFile(form).subscribe(next => {
        console.log(next)
      }, error => {
        console.log(error);
      }
    );
  }

  ngOnInit() {
  }

}
