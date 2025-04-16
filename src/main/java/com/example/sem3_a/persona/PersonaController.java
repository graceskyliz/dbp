package com.example.sem3_a.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaRepository personaRepository; //Creo objeto de tipo personarepository

    @PostMapping
    ResponseEntity<Persona> createPersona(@RequestBody Persona persona){
        return ResponseEntity.ok(personaRepository.save(persona));
    }

    @GetMapping
    ResponseEntity<List<Persona>> getPersonas(){
        return ResponseEntity.ok(personaRepository.findAll());
    }

    @GetMapping("/{id}") // path variable
    ResponseEntity<Persona> getPersona(@PathVariable Long id){
        Optional<Persona> persona = personaRepository.findById(id); // es como  un contenedor
        //si econtra la persona va a estar lleno, pero si no estara vacio

        if (persona.isEmpty()) {
            return null;
        }
        else{
            return ResponseEntity.ok(persona.get());
        }
    }

}
