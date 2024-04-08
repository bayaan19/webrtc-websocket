package com.example.websocket;

import com.example.websocket.model.WebRtcSignalingMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebRtcSignalHandler extends AbstractWebSocketHandler {
    private static final Logger logger = LogManager.getLogger(WebRtcSignalHandler.class);
    private static final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    private static final BiMap<String, String> registeredSessionMap = HashBiMap.create();
    ObjectMapper objectMapper = JsonMapper.builder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS).build();

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
                case REGISTER:
                    /*
                    Replace the session ID in session map with agent ID to identify receiver of request.
                    sessionMap.put(webRtcSignalingMessage.getPayload(), sessionMap.remove(session.getId()));
                     */
                    registeredSessionMap.put(webRtcSignalingMessage.getPayload(), session.getId());
                    break;
                case REQUEST:
                    /*
                    If agent needs to register on the fly, do the stuff here.
                     */
                    /* Other peer identified using an ID and should be registered by active socket connection. */
                    if (registeredSessionMap.containsKey(webRtcSignalingMessage.getTo())) {
                        /* Set the sender peer's session ID as from address. */
                        webRtcSignalingMessage.setFrom(session.getId());
                        /* Set the remote peer's registered session ID as to address. */
                        webRtcSignalingMessage.setTo(registeredSessionMap.get(webRtcSignalingMessage.getTo()));
                        /* Forward the message to other peer. */
                        sessionMap.get(webRtcSignalingMessage.getTo()).sendMessage(new TextMessage(objectMapper.writeValueAsString(webRtcSignalingMessage)));
                    } else {
                        logger.warn("{} yet not registered.", webRtcSignalingMessage.getTo());
                    }
                    break;
                case RESPONSE:
                case OFFER:
                case ANSWER:
                case CANDIDATE:
                    sessionMap.get(webRtcSignalingMessage.getTo()).sendMessage(message);
                    break;
                default:
                    logger.error("Invalid WebRTC signaling message type {}.", webRtcSignalingMessage.getType());
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
        registeredSessionMap.inverse().remove(session.getId());
        sessionMap.remove(session.getId());
    }
}
