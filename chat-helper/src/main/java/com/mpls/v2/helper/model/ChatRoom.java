package com.mpls.v2.helper.model;

import org.springframework.web.socket.WebSocketSession;

import java.util.List;

/**
 * Created by danul on 16.10.2017.
 */
public class ChatRoom {
    private Integer id;
    private List<Message> messages;
    private List<WebSocketSession> callCenter;
    private WebSocketSession client;
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public ChatRoom setId(Integer id) {
        this.id = id;
        return this;
    }

    public List<WebSocketSession> getCallCenter() {
        return callCenter;
    }

    public ChatRoom setCallCenter(List<WebSocketSession> callCenter) {
        this.callCenter = callCenter;
        return this;
    }

    public WebSocketSession getClient() {
        return client;
    }

    public ChatRoom setClient(WebSocketSession client) {
        this.client = client;
        return this;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public ChatRoom setMessages(List<Message> messages) {
        this.messages = messages;
        return this;
    }


    public Boolean getActive() {
        return active;
    }

    public ChatRoom setActive(Boolean active) {
        this.active = active;
        return this;
    }
}
