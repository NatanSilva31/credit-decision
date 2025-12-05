package com.example.creditdecision.dto;

import lombok.Data;

@Data
public class DecisionResponse {
    private String cpf;
    private String resultado;     // APROVADO / NEGADO
    private Double limite;        // Limite aprovado
    private String mensagem;      // Mensagem explicativa
}
