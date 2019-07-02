package com.example.client;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import static android.icu.text.RelativeDateTimeFormatter.AbsoluteUnit.NOW;
import static java.time.LocalDateTime.now;
import static javax.xml.datatype.DatatypeConstants.DATETIME;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "tokopulsa";

    private static final String TABLE_NAME = "transaksi";

    public static final String KEY_ID = "id_transaksi";
    public static final String KEY_TUJUAN= "no_tujuan";
    public static final String KEY_PROVIDER = "provider";
    public static final String KEY_NOMINAL = "nominal";
    public static final String KEY_TANGGAL = "tanggal";

    public DBHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+
                KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                KEY_TUJUAN+" TEXT, "+
                KEY_PROVIDER+" TEXT, "+
                KEY_NOMINAL+" TEXT, "+
                KEY_TANGGAL+" TEXT "+")";
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<HashMap<String,String>> getAllData()
    {
        ArrayList<HashMap<String, String>> transaksiList;

        transaksiList = new ArrayList<HashMap<String, String>>();
        String sql = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do{
                HashMap<String,String> map = new HashMap<String, String>();
                map.put(KEY_ID,cursor.getString(0));
                map.put(KEY_TUJUAN,cursor.getString(1));
                map.put(KEY_PROVIDER,cursor.getString(2));
                map.put(KEY_NOMINAL,cursor.getString(3));
                map.put(KEY_TANGGAL,cursor.getString(4));
                transaksiList.add(map);
            }while (cursor.moveToNext());


        }
        Log.e("select sqlite ", ""+transaksiList);

        db.close();
        return transaksiList;
    }

    public void insert(String no_tujuan, String provider, String nominal, String waktu){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO "+TABLE_NAME+" (no_tujuan,provider,nominal,tanggal) "+
                "VALUES('" + no_tujuan + "','" + provider + "','" + nominal + "','" + waktu+"')";
        Log.e("insert sqlite ", ""+ sql);

        db.execSQL(sql);
        db.close();
    }

    public void update(int id, String no_tujuan, String provider, int nominal) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_NAME + " SET "
                + KEY_TUJUAN + "='" + no_tujuan + "', "
                + KEY_PROVIDER + "='" + provider + "'," + KEY_NOMINAL + "='" + nominal + "'" +
                "" + " WHERE " + KEY_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
