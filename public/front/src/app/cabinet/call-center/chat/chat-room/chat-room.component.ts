import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ChatComponent} from "../chat.component";
import {ChatRoom} from "../../../../../environments/model/chat-room";
import {isUndefined} from "util";
import {Message} from "../../../../../environments/model/message";

@Component({
  selector: 'app-chat-room',
  templateUrl: './chat-room.component.html',
  styleUrls: ['./chat-room.component.css']
})
export class ChatRoomComponent implements OnInit {

  chatRoom: ChatRoom = new ChatRoom();

  constructor() {

    ChatComponent._chatRoom$.subscribe(next => {
      this.chatRoom = next;
    });
    console.log(JSON.stringify(this.chatRoom));

  }
  send(message: HTMLTextAreaElement) {
    console.log(message.value);
    event.preventDefault();
    ChatComponent.ws.send(JSON.stringify(new Message(message.value, "admin", true, true, this.chatRoom.messages[0].chatRoomId))).subscribe(
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
