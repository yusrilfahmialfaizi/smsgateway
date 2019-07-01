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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Pulsa extends AppCompatActivity {
    EditText no_tujuan;
    Spinner provider;
    Spinner nominal;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulsa);

        db = new DBHelper(this);

        Button kembali = (Button) findViewById(R.id.kembali);
        Button kirim = (Button) findViewById(R.id.kirim);
        no_tujuan = (EditText) findViewById(R.id.no_tujuan);
        provider = (Spinner) findViewById(R.id.provider);
        nominal = (Spinner) findViewById(R.id.nominal);

//        String no = no_tujuan.getText();
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
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
        String tanggal = dateFormat.format(calendar.getTime());

        String no = String.valueOf(no_tujuan.getText());
        String pro = String.valueOf(provider.getSelectedItem());
        String nom = String.valueOf(nominal.getSelectedItem());

        db.insert(no,pro, nom, tanggal);


        gt.closeBlocking();

    }
}
