package com.example.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);

        Button pulsa = (Button) findViewById(R.id.pulsa);
        Button data = (Button) findViewById(R.id.data);
        pulsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Pulsa.class);
                startActivity(i);

//                Intent data = new Intent(MainActivity.this, Sms.class);
//                startActivity(data);

            }
        });
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent datas = new Intent(MainActivity.this, Sms.class);
                startActivity(datas);
            }
        });
    }
}
