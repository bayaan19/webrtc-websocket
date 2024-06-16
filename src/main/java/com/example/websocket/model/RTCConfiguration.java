package com.example.websocket.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A minimalist RTCConfiguration.
 *
 * @author Aminul Islam
 */
public class RTCConfiguration {
    /**
     * A list of ICE server's describing servers available to be used by ICE,
     * such as STUN and TURN servers.
     */
    private List<RTCIceServer> iceServers;

    public RTCConfiguration() {
        this.iceServers = new ArrayList<>();
    }

    public List<RTCIceServer> getIceServers() {
        return iceServers;
    }

    public void setIceServers(List<RTCIceServer> iceServers) {
        this.iceServers = iceServers;
    }

    public RTCConfiguration withIceServers(List<RTCIceServer> iceServers) {
        this.iceServers.addAll(iceServers);
        return this;
    }

    public RTCConfiguration withIceServer(RTCIceServer iceServer) {
        this.iceServers.add(iceServer);
        return this;
    }
}
