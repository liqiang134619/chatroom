package com.example.chatroom.config;

import com.example.chatroom.controller.WebSocketServer;
import com.example.chatroom.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Liq
 * @date 2019/5/10
 * 开启websocket的支持
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Autowired
    public void SetChattingService(ChatRecordService chatRecordService) {
        WebSocketServer.recordService = chatRecordService;
    }
}
