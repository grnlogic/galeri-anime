package com.galerianime.anime_gallery.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "anime")
@Getter
@Setter
public class Anime {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String judul;
    private String genre;
    private int tahun;
    private String status;

    @Column(columnDefinition= "TEXT")
    private String sinopsis;
    
    @Column(columnDefinition = "TEXT")
    private String posterImage;

    // Getter methods
    public String getJudul() {
        return judul;
    }

    public String getGenre() {
        return genre;
    }

    public int getTahun() {
        return tahun;
    }

    public String getStatus() {
        return status;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getPosterImage() {
        return posterImage;
    }

    // Setter methods
    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }
}
