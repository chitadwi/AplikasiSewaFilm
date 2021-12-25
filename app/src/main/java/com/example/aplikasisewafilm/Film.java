package com.example.aplikasisewafilm;

//entitas buat modul 4
public class Film {
    private String id;
    private String nama;
    private String judul;
    private String lamapeminjaman;
    private String genre;
    private String rating;
    private String verif;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getLamapeminjaman() {
        return lamapeminjaman;
    }

    public void setLamapeminjaman(String lamapeminjaman) {
        this.lamapeminjaman = lamapeminjaman;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVerif() {
        return verif;
    }

    public void setVerif(String verif) {
        this.verif = verif;
    }
}
