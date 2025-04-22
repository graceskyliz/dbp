package com.example.sem3_a.dto;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewArtistaDto {

    @NotNull
    @Size(min = 1, max = 25)
    private String username;

    private String descripcion;

    @Email
    private String email;

    private List<Long> cancionIdList = new ArrayList<>();
    //esta creando una lista de ids de canciones
    //luego la base de datos se encarga de conectar esta
}