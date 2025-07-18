package com.galerianime.anime_gallery.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.galerianime.anime_gallery.model.Anime;
import com.galerianime.anime_gallery.service.AnimeService;

import java.util.List;

@RestController
@RequestMapping("/api/anime")
public class AnimeController {
    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping
    public List<Anime> getAllAnime() {
        return animeService.getAllAnime();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id) {
        try {
            Anime anime = animeService.getAnimeById(id);
            return ResponseEntity.ok(anime);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public Anime createAnime(@RequestBody Anime anime) {
        return animeService.createAnime(anime);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Anime> updateAnime(@PathVariable Long id, @RequestBody Anime anime) {
        try {
            Anime updatedAnime = animeService.updateAnime(id, anime);
            return ResponseEntity.ok(updatedAnime);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id) {
        try {
            animeService.deleteAnime(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
