package com.galerianime.anime_gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galerianime.anime_gallery.model.Anime;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    // Additional query methods can be defined here if needed
    // For example, findByGenre, findByTahun, etc.  
    
}
