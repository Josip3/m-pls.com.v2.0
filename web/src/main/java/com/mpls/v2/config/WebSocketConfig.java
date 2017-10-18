//package com.mpls.v2.config;
//
//import com.mpls.v2.helper.ChatHelperApplication;
//import com.mpls.v2.helper.handler.ChatHelperHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    @Autowired
//    private ChatHelperHandler chatHelperHandler;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(chatHelperHandler, "/websocket-chat-helper").setAllowedOrigins("*");
//    }
//
//}