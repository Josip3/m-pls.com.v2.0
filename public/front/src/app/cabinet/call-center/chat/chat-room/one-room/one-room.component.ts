import { Component, OnInit } from '@angular/core';
import {Message} from "../../../../../../environments/model/message";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-one-room',
  templateUrl: './one-room.component.html',
  styleUrls: ['./one-room.component.css']
})
export class OneRoomComponent implements OnInit {

  messages:Message;
  chatRoomId:number;

  constructor(private _activatedRoute:ActivatedRoute) {
    this._activatedRoute.params.subscribe(params=>{
      this.chatRoomId = params["id"];
    });
  }

  ngOnInit() {
  }

}
