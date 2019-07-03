package com.example.client;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Sms extends AppCompatActivity {
    public static final String KEY_ID = "id_transaksi";
    public static final String KEY_TUJUAN= "no_tujuan";
    public static final String KEY_PROVIDER = "provider";
    public static final String KEY_NOMINAL = "nominal";
    public static final String KEY_TANGGAL = "tanggal";
    private ArrayList<String> mBarang;
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
        readData();


    }
    public void readData(){
        mBarang = new ArrayList<String>();
        SQLiteDatabase database = SQLite.getReadableDatabase();

        String sql = "SELECT * FROM transaksi ORDER BY id_transaksi DESC";
        PulsaAdapter adapter = new PulsaAdapter(this,mBarang);

        Cursor c = database.rawQuery(sql, null);
        String no_tujuan, provider, nominal,  tanggal;
        int id_transaksi;
        if (c.getCount()>0)
            while (c.moveToNext()){
                id_transaksi = c.getInt(c.getColumnIndex("id_transaksi"));
                no_tujuan = c.getString(c.getColumnIndex("no_tujuan"));
                provider = c.getString(c.getColumnIndex("provider"));
                nominal = c.getString(c.getColumnIndex("nominal"));
                tanggal = c.getString(c.getColumnIndex("tanggal"));

                String item = id_transaksi+"__"+no_tujuan+"__"+provider+"__"+nominal+"__"+tanggal;
                mBarang.add(item);
            }
        list.setAdapter(adapter);
    }
//    private void getAllData(){
//        ArrayList<HashMap<String,String>> row = SQLite.getAllData();
//        mBarang = new ArrayList<HashMap<String,String>>();
//        HashMap<String,String> map = new HashMap<String, String>();
//        for (int i = 0; i<row.size();i++){
//            map.put("id_transaksi",row.get(i).get(KEY_ID));
//            map.put("no_tujuan",row.get(i).get(KEY_TUJUAN));
//            map.put("provider",row.get(i).get(KEY_PROVIDER));
//            map.put("nominal",row.get(i).get(KEY_NOMINAL));
//            map.put("tanggal",row.get(i).get(KEY_TANGGAL));
//            mBarang.add(map);
////            Toast.makeText(this,records.get(i).get(KEY_ID),Toast.LENGTH_SHORT).show();
//            PulsaAdapter adapter = new PulsaAdapter(Sms.this, mBarang);
//            list.setAdapter(adapter);
//            Log.e("Sms", String.valueOf(mBarang));
//        }
//        System.out.println(mBarang);
//    }
}
