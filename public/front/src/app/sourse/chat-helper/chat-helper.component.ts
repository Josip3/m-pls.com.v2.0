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
  message: Message[] = [];
  chatRoomId: number;

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
      this.ws = new $WebSocket(Url.ws);
      this.containerIdVC.nativeElement.style.bottom = "0";
      this.ws.getDataStream().subscribe(
        (msg) => {
          console.log("next", msg.data);
          let mes: Message = JSON.parse(msg.data);
          console.log(msg.data);
          if (mes.create)
            this.message.push(mes);
          // this.ws.close(false);
          this.chatRoomId = mes.chatRoomId;
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
      this.ws.send(JSON.stringify(new Message("delete", "admin", false, false, 1))).subscribe(
        (msg) => {
          console.log("next", msg.data);
        },
        (msg) => {
          console.log("error", msg);
        },
        () => {
          console.log("complete");
        }
      );
    }

  }

  send(message: HTMLTextAreaElement) {
    console.log(message.value);
    event.preventDefault();
    this.ws.send(JSON.stringify(new Message(message.value, "", false, true, this.chatRoomId))).subscribe(
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
