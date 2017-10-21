/**
 * Created by danul on 16.10.2017.
 */
export class Message {
  public text: string;
  public name: string;
  public CallCenter: boolean;
  public create: boolean;
  public chatRoomId: number;

  constructor(text?: string, name?: string, CallCenter?: boolean, create?: boolean, chatRoomId?: number) {
    this.text = text;
    this.name = name;
    this.CallCenter = CallCenter;
    this.chatRoomId = chatRoomId;
    this.create = create;
  }
}
