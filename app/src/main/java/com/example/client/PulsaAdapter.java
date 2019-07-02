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
    private ArrayList<HashMap<String, String>> mBarang;

    public PulsaAdapter(Sms barang, ArrayList<HashMap<String,String>> mBarang) {
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
        holder.id_transaksi.setText(mBarang.get(position).get("id_transaksi"));
        holder.no_tujuan.setText(mBarang.get(position).get("no_tujuan"));
        holder.provider.setText(mBarang.get(position).get("provider"));
        holder.nominal.setText(mBarang.get(position).get("nominal"));
        holder.tanggal.setText(mBarang.get(position).get("tanggal"));
        Toast.makeText(mContext,mBarang.get(position).get("id_transaksi"),Toast.LENGTH_LONG).show();
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
