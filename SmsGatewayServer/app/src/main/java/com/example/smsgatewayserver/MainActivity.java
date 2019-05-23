package com.example.smsgatewayserver;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

import java.io.IOException;
import java.net.UnknownHostException;

public class MainActivity extends Activity {
    private SmsGatewayServer server;

    public MainActivity(){
        try{
//            membuat server dengan port 8989
            server = new SmsGatewayServer(8989);
        }catch (UnknownHostException e){
            Log.e(MainActivity.class.getName(), e.getMessage(), e);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Receiver untuk sendIntent
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        SmsGatewayContainer.notification("SMS Sent",true);
                        break;
                    case SmsManager
                                .RESULT_ERROR_GENERIC_FAILURE:
                        SmsGatewayContainer.notification("Generic Failure",false);
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        SmsGatewayContainer.notification("No Service", false);
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        SmsGatewayContainer.notification("Null PDU", false);
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        SmsGatewayContainer.notification("Radio Off",false);
                        break;
                }
            }
        },new IntentFilter("SMS_SENT"));

        //Receiver untuk delivery
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch(getResultCode())
                {
                    case Activity.RESULT_OK:
                        SmsGatewayContainer.notification("SMS Delivered",true);
                        break;
                    case Activity.RESULT_CANCELED:
                        SmsGatewayContainer.notification("SMS Not Delivered",false);
                        break;
                }
            }
        },new IntentFilter("SMS_DELIVERED"));

        //buat send intent
        PendingIntent sendIntent = PendingIntent.getBroadcast(this,0,
                new Intent("SMS_SENT"),0);
        //buatdeliveryIntent
        PendingIntent deliveryIntent = PendingIntent.getBroadcast(this,0,
                new Intent("SMS_DELIVERED"),0);
        //set sendIntent dan delivery Intent
        server.setSendIntent(sendIntent);
        server.setDeliveryIntent(deliveryIntent);
        server.start();
    }

    @Override
    protected void onDestroy() {
        try{
            server.stop();
        }catch (IOException e){
            Log.e(MainActivity.class.getName(), e.getMessage(), e);
        }catch (InterruptedException e){
            Log.e(MainActivity.class.getName(), e.getMessage(), e);
        }
        super.onDestroy();
    }
}
