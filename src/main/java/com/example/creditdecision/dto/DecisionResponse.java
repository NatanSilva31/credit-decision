package com.example.creditdecision.dto;

import lombok.Data;

@Data // Gera Getters e Setters
public class DecisionResponse {
    private String cpf;
    private String resultado;
    private Double limite;
}