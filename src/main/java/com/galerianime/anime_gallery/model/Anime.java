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
}
