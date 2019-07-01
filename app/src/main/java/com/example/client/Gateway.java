package com.example.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class Gateway extends WebSocketClient {
    public Gateway(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Koneksi Tersambung" + handshakedata);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Server : "+ message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Koneksi Terputus");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("Koneksi Erorr");
    }
}
