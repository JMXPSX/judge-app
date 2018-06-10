package com.judge.dredd.conf;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler{
	@Override
    public void afterConnectionEstablished(
                 WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(
              WebSocketSession session, TextMessage message)
                                               throws Exception {
                System.out.println(">>>> " + message.getPayload());
        }
}