package com.galerianime.anime_gallery.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.galerianime.anime_gallery.DTO.LoginRequest;
import com.galerianime.anime_gallery.DTO.LoginResponse;
import com.galerianime.anime_gallery.model.Anime;
import com.galerianime.anime_gallery.security.JwtUtil;
import com.galerianime.anime_gallery.service.AnimeService;
import com.galerianime.anime_gallery.service.UserService;


@RestController
@RequestMapping("/api/anime")
public class AnimeController {
    private final AnimeService animeService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    // Sesuaikan constructor untuk menerima semua "alat" baru
    // Removed incorrect AuthController constructor
    @Autowired
    public AnimeController(
            AnimeService animeService,
            UserService userService,
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtUtil jwtUtil) {
        this.animeService = animeService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id) {
        Anime anime = animeService.getAnimeById(id);
        if (anime == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public Anime createAnime(@RequestBody Anime anime) {
        return animeService.createAnime(anime);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Anime> updateAnime(@PathVariable Long id, @RequestBody Anime anime) {
        Anime updatedAnime = animeService.updateAnime(id, anime);
        if (updatedAnime == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedAnime);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id) {
        if (animeService.deleteAnime(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // 1. Autentikasi username & password
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        // 2. Jika berhasil, buat token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
        final String token = jwtUtil.generateToken(userDetails);

        // 3. Kirim token sebagai respons
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
