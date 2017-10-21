import {Message} from "./message";
/**
 * Created by danul on 21.10.2017.
 */
export class ChatRoom {
  id: number;
  messages: Message[] = [];
  active: boolean;

}
