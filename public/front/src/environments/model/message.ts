/**
 * Created by danul on 16.10.2017.
 */
export class Message {
  public text: string;
  public name: string;
  public isCallCenter: boolean;
  public chatRoomId: number;

  constructor(text?: string, name?: string, isCallCenter?: boolean, chatRoomId?: number) {
    this.text = text;
    this.name = name;
    this.isCallCenter = isCallCenter;
    this.chatRoomId = chatRoomId;
  }
}
