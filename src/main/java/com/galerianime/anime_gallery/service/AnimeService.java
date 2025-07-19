package com.galerianime.anime_gallery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.galerianime.anime_gallery.model.Anime;
import com.galerianime.anime_gallery.repository.AnimeRepository;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @Transactional(readOnly = true)
    public List<Anime> getAllAnime() {
        return animeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Anime getAnimeById(Long id) {
        return animeRepository.findById(id)
                .orElseThrow();
    }

    @Transactional
    public Anime createAnime(Anime anime) {
        // Anda bisa menambahkan validasi di sini sebelum menyimpan
        return animeRepository.save(anime);
    }

    @Transactional
    public Anime updateAnime(Long id, Anime animeDetails) {
        // getAnimeById sudah menangani kasus "not found", jadi kita tidak perlu cek null lagi.
        Anime existingAnime = getAnimeById(id);

        existingAnime.setJudul(animeDetails.getJudul());
        existingAnime.setGenre(animeDetails.getGenre());
        existingAnime.setTahun(animeDetails.getTahun());
        existingAnime.setStatus(animeDetails.getStatus());
        existingAnime.setSinopsis(animeDetails.getSinopsis());
        existingAnime.setPosterImage(animeDetails.getPosterImage());

        
        return animeRepository.save(existingAnime);
    }

    @Transactional
    public boolean deleteAnime(Long id) {
        // Pertama, cek apakah anime ada. Jika tidak, ResourceNotFoundException akan dilempar.
        Anime animeToDelete = getAnimeById(id);
        animeRepository.delete(animeToDelete);
        return true;
    }


}
