package com.example.websocket;

import com.example.websocket.model.WebRtcSignalingMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.HashMap;

public class WebRtcSignalHandler extends AbstractWebSocketHandler {
    private static final Logger logger = LogManager.getLogger(WebRtcSignalHandler.class);
    private static final HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Connection established with id {}.", session.getId());
        WebSocketSession oldSession = sessionMap.put(session.getId(), session);
        if (oldSession != null) oldSession.close();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("Received text message {} from {}.", message, session.getId());

        session.sendMessage(new TextMessage("Hello."));

        try {
            WebRtcSignalingMessage webRtcSignalingMessage = objectMapper.readValue(message.getPayload(), WebRtcSignalingMessage.class);
            logger.info("Parsed message: {}", webRtcSignalingMessage);

            switch (webRtcSignalingMessage.getType()) {
                case REQUEST:
                    break;
                case RESPONSE:
                    break;
                case OFFER:
                    break;
                case ANSWER:
                    break;
                case CANDIDATE:
                    break;
                default:
                    System.out.println("This is not possible.");
            }
        } catch (JsonProcessingException exception) {
            logger.error("Invalid WebRTC signaling message: ", exception);
        }
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        logger.error("Received binary message {} from {}.", message, session.getId());
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        logger.error("Received pong message {} from {}.", message, session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("Connection closed with id {} as {}.", session.getId(), status.getReason());
        sessionMap.remove(session.getId());
    }
}
