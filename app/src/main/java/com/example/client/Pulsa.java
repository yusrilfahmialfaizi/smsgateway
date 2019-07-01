package com.example.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

public class Pulsa extends AppCompatActivity {
    EditText no_tujuan;
    Spinner provider;
    Spinner nominal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulsa);

        Button kembali = (Button) findViewById(R.id.kembali);
        Button kirim = (Button) findViewById(R.id.kirim);
        no_tujuan = (EditText) findViewById(R.id.no_tujuan);
        provider = (Spinner) findViewById(R.id.provider);
        nominal = (Spinner) findViewById(R.id.nominal);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pulsa.this, MainActivity.class);
                startActivity(intent);
            }
        });

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    kirim_sms();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void kirim_sms() throws URISyntaxException, InterruptedException {
        URI server = new URI("ws://192.168.43.1:8989");
        Gateway gt = new Gateway(server);

        gt.connectBlocking(); //menyalakan koneksi dengan server

        JsonObject ob = new JsonObject();
        ob.add("to", new JsonPrimitive ("" + no_tujuan.getText()));
        ob.add("message", new JsonPrimitive("Terimakasih, isi pulsa "+provider.getSelectedItem()+" dengan nominal "+nominal.getSelectedItem()+" telah berhasil !!!"));

        Gson gson = new Gson();
        String json = gson.toJson(ob);

        gt.send(json);

        gt.closeBlocking();

    }
}
