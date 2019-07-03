package com.example.client;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class PulsaAdapter extends RecyclerView.Adapter<PulsaAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mBarang;

    public PulsaAdapter(Sms barang, ArrayList<String> mBarang) {
        this.mContext = barang;
        this.mBarang = mBarang;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        for (int i = 0;i<mBarang.size();i++){
//            Toast.makeText(mContext,mBarang.get(i).get("id_transaksi"),Toast.LENGTH_LONG).show();
//        }
        String[] row_items = mBarang.get(position).split("__");
        holder.id_transaksi.setText(row_items[0]);
        holder.no_tujuan.setText(row_items[1]);
        holder.provider.setText(row_items[2]);
        holder.nominal.setText(row_items[3]);
        holder.tanggal.setText(row_items[4]);
    }

    @Override
    public int getItemCount() {
        return mBarang.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView id_transaksi;
        TextView no_tujuan;
        TextView provider;
        TextView nominal;
        TextView tanggal;


        public ViewHolder(View itemView) {
            super(itemView);

            id_transaksi = (TextView) itemView.findViewById(R.id.id_transaksi);
            no_tujuan = (TextView) itemView.findViewById(R.id.no_tujuan);
            provider = (TextView) itemView.findViewById(R.id.provider);
            nominal = (TextView) itemView.findViewById(R.id.nominal);
            tanggal = (TextView) itemView.findViewById(R.id.tanggal);


        }
    }

}
