package com.example.sem3_a.Cancion;


import com.example.sem3_a.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/canciones")
public class CancionController {

    @Autowired
    CancionRepository cancionRepository;

    @PostMapping
    public ResponseEntity<Cancion> post(@RequestBody Cancion c) {
        return ResponseEntity.ok(cancionRepository.save(c));
    }
    @GetMapping("{id}")
    public ResponseEntity<Cancion> get(@PathVariable Long id) {
        Optional<Cancion> cancion = cancionRepository.findById(id);
        if (cancion.isPresent()) {
            return ResponseEntity.ok(cancion.get());
        }
        throw new ResourceNotFoundException("La cancion no existe");
    }
}
