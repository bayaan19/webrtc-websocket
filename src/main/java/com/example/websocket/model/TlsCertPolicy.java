package com.example.websocket.model;

public enum TlsCertPolicy {
    /**
     * For TLS based protocols, ensure the connection is secure by not
     * circumventing certificate validation.
     */
    SECURE,

    /**
     * For TLS based protocols, disregard security completely by skipping
     * certificate validation. This is insecure and should never be used unless
     * security is irrelevant in that particular context.
     */
    INSECURE_NO_CHECK;
}
