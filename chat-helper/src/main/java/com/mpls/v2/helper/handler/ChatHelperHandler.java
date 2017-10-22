package com.mpls.v2.helper.handler;

import com.fasterxml.jackson.databind.DeserializationFeature;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            if (chatRoom.getActive()) {

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<WebSocketSessionWrapper> getAllCallCenter() {
        List<WebSocketSessionWrapper> temp = webSocketSessions.stream().filter(socketSession -> !socketSession.getName().equals("")).collect(toList());
        if (temp.size() == 0)
            return new ArrayList<>();
        else
            return temp;
    }

    private ChatRoom getChatRoom(WebSocketSessionWrapper session) {
        if (chatRooms.stream().filter(chatRoom -> chatRoom.getClient().getWebSocketSession().getId().equals(session.getWebSocketSession().getId())).findFirst().isPresent())
            return chatRooms.stream().filter(chatRoom -> chatRoom.getClient().getWebSocketSession().getId().equals(session.getWebSocketSession().getId())).findFirst().get();
        else {
//            throw new NullPointerException();
            return null;
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {

    }

    WebSocketSessionWrapper getWebSocketSessionWrapper(WebSocketSession socketSession) {
        return webSocketSessions.stream().filter(webSocketSessionWrapper -> webSocketSessionWrapper.getWebSocketSession().getId().equals(socketSession.getId())).findFirst().get();
    }

    WebSocketSessionWrapper getWebSocketSessionWrapperByName(String name) {
        for (int i = 0; i < webSocketSessions.size(); i++) {
            if (webSocketSessions.get(i).getName().equals(name)) {
                return webSocketSessions.get(i).setOnline(true);
            }
        }
        webSocketSessions.add(new WebSocketSessionWrapper().setName(name).setOnline(true));
        return webSocketSessions.get(webSocketSessions.size() - 1);
    }

    public ChatRoom getChatRoom(Integer id) {
        for (int i = 0; i < chatRooms.size(); i++) {
            if (chatRooms.get(i).getId().equals(id))
                return chatRooms.get(i);
        }
        return null;
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) {
        System.err.println("------------------------------------handleMessage---" + webSocketMessage.getPayload() + "-----------------------------------------");
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Message mes = null;
        try {
            mes = mapper.readValue(String.valueOf(webSocketMessage.getPayload()), Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println(mes.getCreate());

        if (mes.getCreate()) {
            System.err.println("--------------------------------------------------------------------------------");
            System.err.println("true");
            System.err.println("chatRoom:" + chatRooms.size());
            System.err.println("--------------------------------------------------------------------------------");
            getChatRoom(mes.getChatRoomId()).getMessages().add(mes);
            System.err.println("--------------------------------------------------------------------------------");
            System.err.println("--------------------------------------------------------------------------------");
            System.err.println("length mess:" + getChatRoom(mes.getChatRoomId()).getMessages().size());
            System.err.println("--------------------------------------------------------------------------------");
            System.err.println("--------------------------------------------------------------------------------");
            sendMessage(mes, getChatRoom(mes.getChatRoomId()));
        } else {
//            --------------------------------------------------------------------------------
            if (mes.getCallCenter()) {
                System.err.println("--------------------------------------------------------------------------------");
                System.err.println("mes.getCallCenter()");
                System.err.println("--------------------------------------------------------------------------------");

                getWebSocketSessionWrapperByName(mes.getName()).setWebSocketSession(webSocketSession);
                getAllChatRoomByCallCenter(getWebSocketSessionWrapperByName(mes.getName())).stream().forEach(chatRoom -> {
                    chatRoom.getMessages().stream().forEach(message -> {
                        try {
                            webSocketSession.sendMessage(new TextMessage(mapper.writeValueAsBytes(message)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                });
                getChatRoomsWithOutCallCenter(getWebSocketSessionWrapperByName(mes.getName()));

            } else {

                System.err.println("--------------------------------------------------------------------------------");
                System.err.println("mes.getCallCenter()  else");
                System.err.println("--------------------------------------------------------------------------------");
                webSocketSessions.add(new WebSocketSessionWrapper().setName("").setWebSocketSession(webSocketSession));
                chatRooms.add(new ChatRoom()
                        .setMessages(new ArrayList<>())
                        .setActive(true).setId(index)
                        .setClient(new WebSocketSessionWrapper().setName("").setWebSocketSession(webSocketSession))
                        .setCallCenter(getAllCallCenter())
                );
                try {
                    webSocketSession.sendMessage(new TextMessage(mapper.writeValueAsBytes(new Message().setName("").setText("").setCallCenter(false).setCreate(false).setChatRoomId(index))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                index += 1;
            }
//     --------------------------------------------------------------------------------

        }
    }

    public List<ChatRoom> getChatRoomsWithOutCallCenter(WebSocketSessionWrapper webSocketSessionWrapper) {
        Set<ChatRoom> chatRoom = new HashSet<>();
        final ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < chatRooms.size(); i++) {
            for (int j = 0; j < chatRooms.get(i).getCallCenter().size(); j++) {
                if (chatRooms.get(i).getCallCenter().size() == 0) {
                    chatRooms.get(i).getCallCenter().add(webSocketSessionWrapper);
                    try {
                        chatRooms.get(i).getClient().getWebSocketSession()
                                .sendMessage(new TextMessage(mapper.writeValueAsBytes(new Message().setText("Консультанта  знайдено"))));
                        chatRooms.get(i).getMessages().stream().forEach(message -> {
                            try {
                                webSocketSessionWrapper.getWebSocketSession().sendMessage(new TextMessage(mapper.writeValueAsBytes(message)));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    chatRoom.add(chatRooms.get(i));
                }
            }
        }
        return new ArrayList<>(chatRoom);
    }

    public List<ChatRoom> getAllChatRoomByCallCenter(WebSocketSessionWrapper webSocketSessionWrapper) {
        Set<ChatRoom> chatRoom = new HashSet<>();

        for (int i = 0; i < chatRooms.size(); i++) {

            for (int j = 0; j < chatRooms.get(i).getCallCenter().size(); j++) {
                if (chatRooms.get(i).getCallCenter().get(j).getName().equals(webSocketSessionWrapper.getName()) && chatRooms.get(i).getActive()) {
                    chatRoom.add(chatRooms.get(i));
                }

            }

        }

        return new ArrayList<>(chatRoom);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.err.println("handleTransportError" + webSocketSession.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.err.println("closeStatus:" + closeStatus.getReason());
        System.err.println("WebSocketSession:" + webSocketSession.getId());
        if (webSocketSessions.size() != 0) {
            if (getChatRoom(getWebSocketSessionWrapper(webSocketSession)).getClient().getWebSocketSession().getId() == webSocketSession.getId()) {
                getChatRoom(getWebSocketSessionWrapper(webSocketSession)).setActive(false);
                System.err.println("webSocketSession:" + webSocketSession.getId());
                getWebSocketSessionWrapper(webSocketSession).setOnline(false);
            }
        }

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}