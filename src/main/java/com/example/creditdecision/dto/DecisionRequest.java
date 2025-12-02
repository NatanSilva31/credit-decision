package com.example.creditdecision.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data // Gera Getters, Setters, toString, etc. automaticamente
public class DecisionRequest {

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido") // Valida se o CPF existe matematicamente
    private String cpf;

    @NotNull(message = "A renda mensal é obrigatória")
    @Positive(message = "A renda deve ser maior que zero")
    private Double rendaMensal;

    @NotNull(message = "O score é obrigatório")
    @Min(value = 0, message = "Score não pode ser menor que 0")
    @Max(value = 1000, message = "Score não pode ser maior que 1000")
    private Integer score;
}