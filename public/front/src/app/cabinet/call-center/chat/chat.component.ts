import {Component, OnInit} from '@angular/core';
import {ChatRoom} from "../../../../environments/model/chat-room";
import {$WebSocket} from "angular2-websocket/angular2-websocket";
import {Url} from "../../../../environments/config/url";
import {Message} from "../../../../environments/model/message";
import {Subject} from "rxjs/Subject";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  static list: ChatRoom[] = [];
  indexList: number[] = [];
  ws: $WebSocket;
  message: Message[];

 static  _chatRoom = new Subject<ChatRoom[]>();
  static _chatRoom$ = ChatComponent._chatRoom.asObservable();




  constructor() {
    this.connect();
  }


  connect() {
    this.ws = new $WebSocket(Url.ws);
    this.ws.getDataStream().subscribe(
      (msg) => {
        console.log("next", msg.data);
        let mes: Message = JSON.parse(msg.data);
        if (this.containsChatRoom(mes.chatRoomId))
          ChatComponent.getChatRoom(mes.chatRoomId).messages.push(mes);
        else {
          let chatRoom = new ChatRoom();
          chatRoom.messages.push(mes);
          chatRoom.id = mes.chatRoomId;
          this.indexList.push(mes.chatRoomId);
          chatRoom.active = true;
          ChatComponent.list.push(chatRoom);
        }
        ChatComponent._chatRoom.next(ChatComponent.list);
        // this.ws.close(false);
      },
      (msg) => {
        console.log("error", msg);
      },
      () => {
        console.log("complete");
      }
    );
    setTimeout(() => {


      this.ws.send(JSON.stringify(new Message("", "admin", true, false, -1))).subscribe(
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
    for (let i = 0; i < ChatComponent.list.length; i++) {
      if (id == ChatComponent.list[i].id)
        return true;
    }
    return false;
  }

  static getChatRoom(id: number): ChatRoom {
    for (let i = 0; i < ChatComponent.list.length; i++) {
      if (id == ChatComponent.list[i].id)
        return ChatComponent.list[i];
    }
  }


}
