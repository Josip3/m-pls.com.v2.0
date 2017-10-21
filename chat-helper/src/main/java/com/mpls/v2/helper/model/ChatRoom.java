package com.mpls.v2.helper.model;

import java.util.List;

/**
 * Created by danul on 16.10.2017.
 */
public class ChatRoom {
    private Integer id;
    private List<Message> messages;
    private List<WebSocketSessionWrapper> callCenter;
    private WebSocketSessionWrapper client;
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public ChatRoom setId(Integer id) {
        this.id = id;
        return this;
    }

    public List<WebSocketSessionWrapper> getCallCenter() {
        return callCenter;
    }

    public ChatRoom setCallCenter(List<WebSocketSessionWrapper> callCenter) {
        this.callCenter = callCenter;
        return this;
    }

    public WebSocketSessionWrapper getClient() {
        return client;
    }

    public ChatRoom setClient(WebSocketSessionWrapper client) {
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
