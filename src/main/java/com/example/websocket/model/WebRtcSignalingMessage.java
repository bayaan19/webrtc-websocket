package com.example.websocket.model;

public class WebRtcSignalingMessage {
    /**
     * Unique ID of the sender (e.g. proxy-id, logged-in-user-session-id)
     */

    private String from;
    /**
     * Unique ID of the receiver (e.g. proxy-id, logged-in-user-session-id)
     */
    private String to;

    /**
     * String representing the message type (e.g., "request", "response" "offer",
     * "answer", "candidate").
     */
    private WebRtcSignalingMessageType type;

    /**
     * JSON String representing data for message type.
     * <p></p>
     * <table>
     * 	<col width="30%"/>
     * 	<col width="35%"/>
     * 	<col width="35%"/>
     * 	<tr>
     * 		<th>Type</th>
     * 		<th>Payload</th>
     * 		<th>Example</th>
     * 	</tr>
     * 	<tr>
     * 		<td>register</td>
     * 		<td>ID of agent responding to RTSP URLs.</td>
     * 		<td>12345</td>
     * 	</tr>
     * 	<tr>
     * 		<td>request</td>
     * 		<td>Comma separated requested RTSP URLs.</td>
     * 		<td>rtsp://admin:password@192.168.0.1:554/Streaming/Channels/1,...</td>
     * 	</tr>
     * 	<tr>
     * 		<td>response</td>
     * 		<td>Comma separated get-at-able RTSP URLs.</td>
     * 		<td>rtsp://admin:password@192.168.0.1:554/Streaming/Channels/1,...</td>
     * 	</tr>
     * 	<tr>
     * 		<td>offer, answer, candidate</td>
     * 		<td>JSON string of local description i.e., SDP or ICE candidate as
     * 		applicable.</td>
     * 		<td>{"type":"offer","sdp":"v=0\r\no=- 1017585816325871969 2 IN IP4
     * 		127.0.0.1\r\na=max-message-size:262144\r\n"}</td>
     * 	</tr>
     * </table>
     */
    private String payload;

    public WebRtcSignalingMessage(String configuration) {
        this.payload = configuration;
        this.type = WebRtcSignalingMessageType.CONFIG;
    }

    public final String getFrom() {
        return from;
    }

    public final void setFrom(String from) {
        this.from = from;
    }

    public final String getTo() {
        return to;
    }

    public final void setTo(String to) {
        this.to = to;
    }

    public final WebRtcSignalingMessageType getType() {
        return type;
    }

    public final void setType(WebRtcSignalingMessageType type) {
        this.type = type;
    }

    public final String getPayload() {
        return payload;
    }

    public final void setPayload(String payload) {
        this.payload = payload;
    }
}
