package com.example.smsgatewayserver;

import android.app.PendingIntent;
import android.telephony.SmsManager;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class SmsGatewayServer extends WebSocketServer {

    private PendingIntent sendIntent;
    private PendingIntent deliveryIntent;

    public SmsGatewayServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public void setSendIntent(PendingIntent sendIntent)
    {
        this.sendIntent = sendIntent;
    }

    public void setDeliveryIntent(PendingIntent deliveryIntent)
    {
        this.deliveryIntent = deliveryIntent;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
//      tambahkan client ke container
        SmsGatewayContainer.add(conn);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
//        hapus client dari container
        SmsGatewayContainer.remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        try{
            JSONObject object = new JSONObject(message);

            String to = object.getString("to");
            String smsMessage = object.getString("message");

            SmsManager.getDefault().sendTextMessage(to,null,smsMessage,sendIntent,deliveryIntent);
            //kirim pesan sukses
            Map<String, String>map = new HashMap<String, String>();
            map.put("type","success");
            map.put("message", "Success Send SMS");
            JSONObject response = new JSONObject(map);
            conn.send(response.toString());
        }catch (JSONException e){
            //kirim pesan eror
            Map<String, String>map = new HashMap<String, String>();
            map.put("type","error");
            map.put("message", "Wrong JSON Format");
            JSONObject response = new JSONObject(map);
            conn.send(response.toString());
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {

    }
}
