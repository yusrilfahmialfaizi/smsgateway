package com.example.client;

public class Helpset {
    public static String id_transaksi, no_tujuan,provider,nominal,tanggal;

    public static String getId_transaksi() {
        return id_transaksi;
    }

    public static void setId_transaksi(String id_transaksi) {
        Helpset.id_transaksi = id_transaksi;
    }

    public static String getNo_tujuan() {
        return no_tujuan;
    }

    public static void setNo_tujuan(String no_tujuan) {
        Helpset.no_tujuan = no_tujuan;
    }

    public static String getProvider() {
        return provider;
    }

    public static void setProvider(String provider) {
        Helpset.provider = provider;
    }

    public static String getNominal() {
        return nominal;
    }

    public static void setNominal(String nominal) {
        Helpset.nominal = nominal;
    }

    public static String getTanggal() {
        return tanggal;
    }

    public static void setTanggal(String tanggal) {
        Helpset.tanggal = tanggal;
    }
}
