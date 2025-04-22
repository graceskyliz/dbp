package com.example.sem3_a.Artista;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByUsername(String username);
}