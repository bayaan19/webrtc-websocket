package com.example.websocket.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A minimalist RTCIceServer.
 *
 * @author Aminul Islam
 */
public class RTCIceServer {
    /**
     * List of STUN or TURN URI(s) associated with this server.
     */
    private List<String> urls;
    /**
     * The username to be used when the TURN server requests authorization.
     */
    private String username;
    /**
     * The credential/password to be used when the TURN server requests
     * authorization.
     */
    private String credential, password;
    /**
     * The TLS certificate policy.
     */
    private TlsCertPolicy tlsCertPolicy;
    /**
     * If the URIs in {@link #urls} only contain IP addresses, this field can be
     * used to indicate the hostname, which may be necessary for TLS (using the
     * SNI extension). If {@link #urls} itself contains the hostname, this isn't
     * necessary.
     */
    private String hostname;

    /**
     * Creates an instance of RTCIceServer.
     */
    public RTCIceServer() {
        this.urls = new ArrayList<>();
        this.tlsCertPolicy = TlsCertPolicy.SECURE;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public RTCIceServer withUrl(String url) {
        this.urls.add(url);
        return this;
    }

    public RTCIceServer withUrls(List<String> urls) {
        this.urls.addAll(urls);
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RTCIceServer withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
        this.password = credential;
    }

    public RTCIceServer withCredential(String credential) {
        this.credential = credential;
        this.password = credential;
        return this;
    }

    public TlsCertPolicy getTlsCertPolicy() {
        return tlsCertPolicy;
    }

    public void setTlsCertPolicy(TlsCertPolicy tlsCertPolicy) {
        this.tlsCertPolicy = tlsCertPolicy;
    }

    public RTCIceServer withTlsCertPolicy(TlsCertPolicy tlsCertPolicy) {
        this.tlsCertPolicy = tlsCertPolicy;
        return this;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public RTCIceServer withHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }
}
