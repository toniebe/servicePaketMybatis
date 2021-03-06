package com.project.Transaksi.model;



import java.io.Serializable;
import java.util.Date;


public class Transaksi implements Serializable{
    private int idtransaksi;
    private String username;
    private String nomer;
    private String provider;
    private String paket;
    private float harga;
    private Date tanggal;


    public Transaksi(int idtransaksi, String username, String nomer, String provider, String paket, float harga, Date tanggal) {
        this.idtransaksi = idtransaksi;
        this.username = username;
        this.nomer = nomer;
        this.provider = provider;
        this.paket = paket;
        this.harga = harga;
        this.tanggal = tanggal;
    }

    public Transaksi(String nomer, String provider, String paket, float harga, Date tanggal) {
        this.nomer = nomer;
        this.provider = provider;
        this.paket = paket;
        this.harga = harga;
        this.tanggal = tanggal;
    }

    @Override
    public String toString() {
        return "Transaksi{" +
                "username='" + username + '\'' +
                ", nomer='" + nomer + '\'' +
                ", provider='" + provider + '\'' +
                ", paket='" + paket + '\'' +
                ", harga=" + harga +
                '}';
    }

    public Transaksi() {
    }

    public int getIdtransaksi() {
        return idtransaksi;
    }

    public void setIdtransaksi(int idtransaksi) {
        this.idtransaksi = idtransaksi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
