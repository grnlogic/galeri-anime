package com.galerianime.anime_gallery.repository;


import com.galerianime.anime_gallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA akan otomatis membuat query untuk mencari user
    // berdasarkan username dari nama method ini.
    Optional<User> findByUsername(String username);
}