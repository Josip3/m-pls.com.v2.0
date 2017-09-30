import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-gallery-container',
  templateUrl: './gallery-container.component.html',
  styleUrls: ['./gallery-container.component.css']
})
export class GalleryContainerComponent implements OnInit {

  constructor() {
    this.test();
    this.auto();
  }

  stop: boolean = false;

  auto() {
    setTimeout(() => {
      if (!this.stop) {
        this.next();
        this.auto();
      }

    }, 5000)
  }

  index: number = 0;

  nextIndex: number = 0;

  video: string[] = [];

  image: string[] = [];

  ngOnInit() {

  }

  back() {
    this.stop = true;
    if (this.nextIndex > 0)
      this.nextIndex -= 1;
    else
      this.nextIndex = this.image.length + this.video.length - 1;
    setTimeout(() => {
      if (this.stop) {
        this.stop = false;
        this.auto();
      }
    }, 5000)
  }

  next() {
    this.stop = true;
    if (this.nextIndex < this.image.length + this.video.length - 1)
      this.nextIndex += 1;
    else {
      this.nextIndex = 0;

    }
    setTimeout(() => {
      if (this.stop) {
        this.stop = false;
        this.auto();
      }
    }, 5000)
  }

  test() {
    this.image.push("https://www.google.com.ua/doodle4google/images/splashes/featured.png");
    this.image.push("https://edu.google.com/images/trust/how-does-google-ensure-its-tools-are-reliable.svg");
    this.image.push("https://internetdevels.ua/sites/default/files/public/styles/937s/public/blog_preview/google-ranking-factors.png?itok=LATziTdE");
    this.video.push("http://m-pls.com/img/video.mp4");
    this.video.push("https://youtu.be/5USgglXBA4s");
    this.video.push("https://youtu.be/AwVHjjnn8pw");
  }

}
