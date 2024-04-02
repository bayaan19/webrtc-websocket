package com.example.websocket;

public class WebRtcSignalingMessage {
    /**
     * Numeric unique ID of the sender (e.g. proxy-id, logged-in-user-seassion-id)
     */

    private Long from;
    /**
     * Numeric unique ID of the receiver (e.g. proxy-id, logged-in-user-seassion-id)
     */
    private Long to;

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
     * 		<td>request</td>
     * 		<td>Array of requested RTSP URLs.</td>
     * 		<td>["rtsp://admin:password@192.168.0.1:554/Streaming/Channels/1"]</td>
     * 	</tr>
     * 	<tr>
     * 		<td>response</td>
     * 		<td>Array of get-at-able RTSP URLs.</td>
     * 		<td>["rtsp://admin:password@192.168.0.1:554/Streaming/Channels/1"]</td>
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

    public final Long getFrom() {
        return from;
    }

    public final void setFrom(Long from) {
        this.from = from;
    }

    public final Long getTo() {
        return to;
    }

    public final void setTo(Long to) {
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
