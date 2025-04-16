package com.example.sem3_a.Artista;


import com.example.sem3_a.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/artista")


public class ArtistaController {
    @Autowired
    ArtistaRepository artistaRepository;

    @PostMapping
    ResponseEntity<Artista> post(@RequestBody Artista artista) {
        return ResponseEntity.ok(artistaRepository.save(artista));
    }

    @GetMapping("{id}") //acceder a la lista de artistas creados con las persona
    ResponseEntity<Artista> get(@PathVariable Long id) {
        Optional<Artista> artista = artistaRepository.findById(id);
        if (artista.isPresent()) {
            return ResponseEntity.ok(artista.get()); // enviar una respuesta
            //es para el cliente, sabe con que status
        }
        throw new ResourceNotFoundException("El artista " + id + " no fue encontrado");
    }
}
