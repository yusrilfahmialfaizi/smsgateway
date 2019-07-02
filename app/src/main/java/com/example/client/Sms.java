package com.example.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class Sms extends AppCompatActivity {
    public static final String KEY_ID = "id_transaksi";
    public static final String KEY_TUJUAN= "no_tujuan";
    public static final String KEY_PROVIDER = "provider";
    public static final String KEY_NOMINAL = "nominal";
    public static final String KEY_TANGGAL = "tanggal";
    private ArrayList<HashMap<String,String>> records = new ArrayList<HashMap<String, String>>();
    private RecyclerView list;
    DBHelper SQLite = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        list = (RecyclerView) findViewById(R.id.recyclerview_sms);
        SQLite = new DBHelper(getApplicationContext());
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(llm);
        getAllData();
        PulsaAdapter adapter = new PulsaAdapter(Sms.this, records);
        list.setAdapter(adapter);
    }
    private void getAllData(){
        ArrayList<HashMap<String,String>> row = SQLite.getAllData();
        HashMap<String,String> map = new HashMap<String, String>();
        for (int i = 0; i<row.size();i++){
            map.put("id_transaksi",row.get(i).get(KEY_ID));
            map.put("no_tujuan",row.get(i).get(KEY_TUJUAN));
            map.put("provider",row.get(i).get(KEY_PROVIDER));
            map.put("nominal",row.get(i).get(KEY_NOMINAL));
            map.put("tanggal",row.get(i).get(KEY_TANGGAL));
            records.add(map);
            Log.e("Sms", String.valueOf(records));
        }

    }
}
