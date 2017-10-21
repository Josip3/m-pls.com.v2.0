import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Message} from "../../../environments/model/message";
import {$WebSocket} from "angular2-websocket/angular2-websocket";
import {Url} from "../../../environments/config/url";

@Component({
  selector: 'app-chat-helper',
  templateUrl: './chat-helper.component.html',
  styleUrls: ['./chat-helper.component.css']
})
export class ChatHelperComponent implements OnInit {

  ws: $WebSocket;
  @ViewChild('containerId') containerIdVC: ElementRef;
  connect: boolean = false;

  constructor() {
  }

  disconnector() {
    this.containerIdVC.nativeElement.style.bottom = "-40vh";
    this.connect = false;
    this.ws.onClose(() => {
      this.connect = false;
      this.containerIdVC.nativeElement.style.bottom = "-40vh";
    });
    this.ws.close();
  }

  connector() {
    if (this.connect)
      this.disconnector();
    else {

      this.containerIdVC.nativeElement.style.bottom = "0";
      this.ws = new $WebSocket(Url.ws);
      this.ws.getDataStream().subscribe(
        (msg) => {
          console.log("next", msg.data);
          this.message.push(JSON.parse(msg.data));
          // this.ws.close(false);
          this.connect = true;
        },
        (msg) => {
          console.log("error", msg);
        },
        () => {
          console.log("complete");
          this.connect = true;
        }
      );
    }

  }

  message: Message[] = [];

  send(message: HTMLTextAreaElement) {
    console.log(message.value);
    event.preventDefault();
    this.ws.send(JSON.stringify(new Message(message.value))).subscribe(
      (msg) => {
        console.log("next", msg.data);
        message.value = "";
      },
      (msg) => {
        console.log("error", msg);
      },
      () => {
        console.log("complete");
        message.value = "";
      }
    );
  }

  ngOnInit() {
  }

}
