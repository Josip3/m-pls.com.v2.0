package com.mpls.v2.helper.model;

/**
 * Created by danul on 16.10.2017.
 */
public class Message {

    private Boolean isCallCenter;
    private String text;
    private String name;
    private Integer chatRoomId;


    public Boolean getCallCenter() {
        return isCallCenter;
    }

    public Message setCallCenter(Boolean callCenter) {
        isCallCenter = callCenter;
        return this;
    }

    public Integer getChatRoomId() {
        return chatRoomId;
    }

    public Message setChatRoomId(Integer chatRoomId) {
        this.chatRoomId = chatRoomId;
        return this;
    }

    public String getText() {
        return text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public String getName() {
        return name;
    }

    public Message setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                '}';
    }
}
