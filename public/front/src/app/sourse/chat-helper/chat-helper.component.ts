import {Component, OnInit} from '@angular/core';
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

  constructor() {
    this.connect();
  }

  connect() {
    this.ws = new $WebSocket(Url.ws);
    this.ws.getDataStream().subscribe(
      (msg) => {
        console.log("next", msg.data);
        this.message.push(JSON.parse(msg.data));
        // this.ws.close(false);
      },
      (msg) => {
        console.log("error", msg);
      },
      () => {
        console.log("complete");
      }
    );
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
