import {Component, OnInit} from '@angular/core';
import {ChatRoom} from "../../../../environments/model/chat-room";
import {$WebSocket} from "angular2-websocket/angular2-websocket";
import {Url} from "../../../../environments/config/url";
import {Message} from "../../../../environments/model/message";
import {Subject} from "rxjs/Subject";
import {ChatRoomComponent} from "./chat-room/chat-room.component";
import {isUndefined} from "util";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  indexList: number[] = [];
 static ws: $WebSocket;
  message: Message[];

  list: ChatRoom[] = [];
  static room: ChatRoom;
  static _chatRoom = new Subject<ChatRoom>();
  static _chatRoom$ = ChatComponent._chatRoom.asObservable();


  constructor() {
    this.connect();
  }


  connect() {
    ChatComponent.ws = new $WebSocket(Url.ws);
    ChatComponent.ws.getDataStream().subscribe(
      (msg) => {
        console.log("next", msg.data);
        let mes: Message = JSON.parse(msg.data);
        if (this.containsChatRoom(mes.chatRoomId))
          this.getChatRoom(mes.chatRoomId).messages.push(mes);
        else {
          let chatRoom = new ChatRoom();
          chatRoom.messages.push(mes);
          chatRoom.id = mes.chatRoomId;
          this.indexList.push(mes.chatRoomId);
          chatRoom.active = true;
          this.list.push(chatRoom);
        }
        if(!isUndefined(ChatComponent.room))
        if (ChatComponent.room.id == mes.chatRoomId) {
          ChatComponent._chatRoom.next(this.getChatRoom(mes.chatRoomId));
        }


      },
      (msg) => {
        console.log("error", msg);
      },
      () => {
        console.log("complete");
      }
    );
    setTimeout(() => {


      ChatComponent.ws.send(JSON.stringify(new Message("", "admin", true, false, -1))).subscribe(
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

    }, 1000);
  }

  ngOnInit() {
  }

  containsChatRoom(id: number): boolean {
    for (let i = 0; i < this.list.length; i++) {
      if (id == this.list[i].id)
        return true;
    }
    return false;
  }

  getChatRoom(id: number): ChatRoom {
    for (let i = 0; i < this.list.length; i++) {
      if (id == this.list[i].id) {
        return this.list[i];
      }
    }
  }

  activeChatRooom(id: number) {
    for (let i = 0; i < this.list.length; i++) {
      if (id == this.list[i].id) {
        ChatComponent.room = this.list[i];
        ChatComponent._chatRoom.next(ChatComponent.room);
      }
    }
  }


}
