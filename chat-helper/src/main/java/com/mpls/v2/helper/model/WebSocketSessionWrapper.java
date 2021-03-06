package com.mpls.v2.helper.model;

import org.springframework.web.socket.WebSocketSession;

/**
 * Created by danul on 21.10.2017.
 */
public class WebSocketSessionWrapper {

    private String name;
    private WebSocketSession webSocketSession;
    private Boolean online;

    public Boolean getOnline() {
        return online;
    }

    public WebSocketSessionWrapper setOnline(Boolean online) {
        this.online = online;
        return this;
    }

    public String getName() {
        return name;
    }

    public WebSocketSessionWrapper setName(String name) {
        this.name = name;
        return this;
    }

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public WebSocketSessionWrapper setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
        return this;
    }
}
