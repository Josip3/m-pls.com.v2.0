package com.mpls.v2.helper.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpls.v2.helper.model.ChatRoom;
import com.mpls.v2.helper.model.Message;
import com.mpls.v2.helper.model.WebSocketSessionWrapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * Created by danul on 16.10.2017.
 */
@Component
@EnableScheduling
@EnableWebSocket
public class ChatHelperHandler implements WebSocketHandler {

    private List<WebSocketSessionWrapper> webSocketSessions = new ArrayList<>();
    private List<ChatRoom> chatRooms = new ArrayList<>();
    private Integer index = 1;

    private void sendMessage(final Message message, ChatRoom chatRoom) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            if (chatRoom.getCallCenter().size() != 0) {
                chatRoom.getClient().getWebSocketSession().sendMessage(new TextMessage(mapper.writeValueAsBytes(message)));
                chatRoom.getCallCenter().forEach(socketSession1 -> {
                    try {
                        socketSession1.getWebSocketSession().sendMessage(new TextMessage(mapper.writeValueAsBytes(message)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                chatRoom.getClient().getWebSocketSession().sendMessage(new TextMessage(mapper.writeValueAsBytes(message.setText("Очікуйте консультанта"))));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<WebSocketSessionWrapper> getAllCallCenter() {
        return webSocketSessions.stream().filter(socketSession -> ofNullable(socketSession.getName()).isPresent()).collect(toList());
    }

    private ChatRoom getChatRoom(WebSocketSessionWrapper session) {
        if (chatRooms.stream().filter(chatRoom -> chatRoom.getClient().equals(session)).findFirst().isPresent())
            return chatRooms.stream().filter(chatRoom -> chatRoom.getClient().equals(session)).findFirst().get();
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        webSocketSessions.add(webSocketSession);
        if (ofNullable(webSocketSession.getPrincipal()).isPresent() && ofNullable(webSocketSession.getPrincipal().getName()).isPresent()) {
            for (int i = 0; i < chatRooms.size(); i++) {
                if (chatRooms.get(i).getCallCenter().size() == 0) {
                    chatRooms.get(i).getCallCenter().add(webSocketSession);
                    if (webSocketSession.getPrincipal() != null && chatRooms.get(i).getClient() != null && chatRooms.get(i).getClient().isOpen()) {
                        chatRooms.get(i).getClient().sendMessage(new TextMessage(new ObjectMapper().writeValueAsBytes(new Message().setText("зєднано з консультантом").setName("bot"))));
                        System.err.println(chatRooms.get(i).getClient().getId());
                    }
                }
            }
        } else {
            int index = this.index++;
            ChatRoom chatRoom = null;
            try {
                if (getAllCallCenter().size() == 0) {
                    chatRoom = new ChatRoom().setId(index).setActive(true).setCallCenter(new ArrayList<>()).setClient(webSocketSession).setMessages(new ArrayList<>());
                    webSocketSession.sendMessage(new TextMessage(new ObjectMapper().writeValueAsBytes(new Message().setText("Очікуйте консультанта").setName("bot"))));
                    System.err.println(getAllCallCenter().size());
                    System.err.println(webSocketSession.getPrincipal());
                } else {
                    chatRoom = new ChatRoom().setId(index).setActive(true).setCallCenter(getAllCallCenter()).setClient(webSocketSession).setMessages(new ArrayList<>());
                }
            } catch (NullPointerException e) {
                chatRoom = (new ChatRoom().setId(index).setActive(true).setCallCenter(new ArrayList<>()).setClient(webSocketSession).setMessages(new ArrayList<>()));
                webSocketSession.sendMessage(new TextMessage(new ObjectMapper().writeValueAsBytes(new Message().setText("Очікуйте консультанта").setName("bot"))));
            } finally {
                chatRooms.add(chatRoom);
            }
        }


    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message mes = mapper.readValue(String.valueOf(webSocketMessage.getPayload()), Message.class);
        System.err.println("webSocketMessage:" + mes.toString());
        sendMessage(mes, getChatRoom(webSocketSession));
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.err.println("handleTransportError" + webSocketSession.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.err.println("closeStatus:" + closeStatus.getReason());
        System.err.println("WebSocketSession:" + webSocketSession.getId());

        if (!ofNullable(webSocketSession.getPrincipal()).isPresent() && ofNullable(ofNullable(webSocketSession.getPrincipal().getName())).isPresent()) {
            getChatRoom(webSocketSession).setActive(false);
            System.err.println("webSocketSession:" + webSocketSession.getId());
            webSocketSessions.remove(webSocketSession);
        }

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}