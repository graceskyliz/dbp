package com.example.sem3_a.Artista;

import com.example.sem3_a.Cancion.Cancion;
import com.example.sem3_a.Cancion.CancionController;
import com.example.sem3_a.Cancion.CancionRepository;
import com.example.sem3_a.Exceptions.ResourceConflictException;
import com.example.sem3_a.Exceptions.ResourceNotFoundException;
import com.example.sem3_a.dto.ArtistaResponseDto;
import com.example.sem3_a.dto.NewArtistaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/artista")

/*
Hasta ahora el proyecto recibe un nuevo artista, pero no se me permite ver la lista completa de
artistas con get
------------------------------
Cambios realizados:
("/all") permite que el usuario vea el username y las canciones del artista
el correo y descripción de este no se muestran, por seguridad.
*/


public class ArtistaController {
    @Autowired
    ArtistaRepository artistaRepository;
    @Autowired
    private CancionRepository cancionRepository;
    //inyecta dependencias necesarias (basicas de proyecto)

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Artista> createArtista(@RequestBody NewArtistaDto artista) {
        Artista nuevoArtista = new Artista();
        nuevoArtista.setUsername(artista.getUsername());
        nuevoArtista.setDescripcion(artista.getDescripcion());
        nuevoArtista.setEmail(artista.getEmail());

        // Busca las canciones por su ID
        List<Cancion> canciones = cancionRepository.findAllById(artista.getCancionIdList());
        nuevoArtista.setCanciones(canciones);

        // Verifica si el Artista ya existe
        Optional<Artista> foundArtistaByUsername = artistaRepository.findByUsername(nuevoArtista.getUsername());
        if (foundArtistaByUsername.isPresent()) {
            throw new ResourceConflictException("Artista already exists");
        }
        // Guarda el nuevo Artista
        Artista savedArtista = artistaRepository.save(nuevoArtista);
        return ResponseEntity.ok(savedArtista);
    }


    /* Aqui se busca una canción mediante id, pero no se usa dtos
    Es decir, esta trabajando directamente con la base de datos
    @GetMapping("/{id}") //acceder a la lista de artistas creados con las persona
    ResponseEntity<Artista> get(@PathVariable Long id) {
        Optional<Artista> artista = artistaRepository.findById(id);
        if (artista.isPresent()) {
            return ResponseEntity.ok(artista.get()); // enviar una respuesta
            //es para el cliente, sabe con que status
        }
        throw new ResourceNotFoundException("El artista " + id + " no fue encontrado");
    }

     */
    @GetMapping("/{id}") //acceder a la lista de artistas creados con las persona
    ResponseEntity<ArtistaResponseDto> get(@PathVariable Long id) {
        Optional<Artista> artista = artistaRepository.findById(id);
        if (artista.isPresent()) {
            Artista artistaa = artista.get();
            ArtistaResponseDto artistaDto = new ArtistaResponseDto();
            modelMapper.map(artistaa, artistaDto);

            for (Cancion cancion :artistaa.getCanciones()){
                artistaDto.getCancionIdList().add(cancion.getId());
            } // enviar una respuesta
            //es para el cliente, sabe con que status
            return ResponseEntity.ok(artistaDto);
        }
        throw new ResourceNotFoundException("El artista " + id + " no fue encontrado");
    }
    /*
    @GetMapping("/all") //muestra todos los artistas en consola
    ResponseEntity<List<Artista>> getAll() {
        return ResponseEntity.ok(artistaRepository.findAll());
    }
    Aqui se conecta con la base de datos pero el dto no esta operando
    */

    @GetMapping("/all")
    public List<ArtistaResponseDto> getAll(){
        //accedes a la base de datos para obtener la información completa
        List<Artista> artistaList = artistaRepository.findAll();

        //ahora artistaList es una lista de Artistas


        //stream() -> convierte esa lista en tipo Stream para poder realizar map
        //map() -> transforma cada objeto Artista a ArtistResponseDto
        List<ArtistaResponseDto> artdtolis = artistaList.stream().map(a ->{
            ArtistaResponseDto dto = new ArtistaResponseDto(); //se crea un objeto Dto
            dto.setUsername(a.getUsername()); // copia el username de la entidad al dto
            List<Long> ids = a.getCanciones().stream().map(Cancion::getId).collect(Collectors.toList());
            dto.setCancionIdList(ids);//para cada cancion saca su id
            return dto;
        }).collect(Collectors.toList());

        return artdtolis; //ArtistaDtoLista

    }
}
