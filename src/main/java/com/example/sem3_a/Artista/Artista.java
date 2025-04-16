package com.example.sem3_a.Artista;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity // necesita un id
@Data
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    private Long id; //tipo UUID id, son ids unicos y complejos
    @OneToMany(mappedBy ="artista",cascade = CascadeType.ALL)
    private List<Artista> artistas;


}
