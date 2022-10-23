package com.example.systemparking.model;

public class User {
    String id, snipper, nama, nomor, merek, tipe, tahun, tanggal;

    public User(){

    }

    public User(String snipper, String nama, String nomor, String merek, String tipe, String tahun, String tanggal) {
        this.snipper = snipper;
        this.nama = nama;
        this.nomor = nomor;
        this.merek = merek;
        this.tipe = tipe;
        this.tahun = tahun;
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSnipper() {
        return snipper;
    }

    public void setSnipper(String snipper) {
        this.snipper = snipper;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
