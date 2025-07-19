package com.galerianime.anime_gallery.service;

import com.galerianime.anime_gallery.model.Role;
import com.galerianime.anime_gallery.model.User;
import com.galerianime.anime_gallery.repository.UserRepository;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

@Transactional
public User registerUser(User user) {
    // Enkripsi password sebelum disimpan
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);

    // Tetapkan role: jika ini user pertama, jadi ADMIN. Jika tidak, jadi USER.
    if (userRepository.count() == 0) {
        user.setRole(Role.ROLE_ADMIN);
    } else {
        user.setRole(Role.ROLE_USER);
    }

    return userRepository.save(user);
}

    
}
