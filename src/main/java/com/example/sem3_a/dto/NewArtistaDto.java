package com.example.sem3_a.dto;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class NewArtistaDto {

    @NotNull
    @Size(min = 1, max = 25)
    private String username;

    private String descripcion;

    @Email
    private String email;

}