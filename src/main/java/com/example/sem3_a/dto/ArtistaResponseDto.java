package com.example.sem3_a.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class ArtistaResponseDto {
    private String username;
    private List<Long> cancionIdList = new ArrayList<>();
}
