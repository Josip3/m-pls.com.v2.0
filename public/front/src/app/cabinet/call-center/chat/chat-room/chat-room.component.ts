import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ChatComponent} from "../chat.component";
import {ChatRoom} from "../../../../../environments/model/chat-room";
import {isUndefined} from "util";

@Component({
  selector: 'app-chat-room',
  templateUrl: './chat-room.component.html',
  styleUrls: ['./chat-room.component.css']
})
export class ChatRoomComponent implements OnInit {

  chatRoom: ChatRoom;

  constructor(private route: ActivatedRoute) {

    this.route.params.subscribe(params => {
      ChatComponent._chatRoom$.subscribe(next => {
        if (!isUndefined(ChatComponent.getChatRoom(parseInt(params["id"]))))
          this.chatRoom = ChatComponent.getChatRoom(parseInt(params["id"]));
        else
          this.chatRoom = new ChatRoom();
      });
    });
    console.log(JSON.stringify(this.chatRoom));

  }

  ngOnInit() {
  }

}
