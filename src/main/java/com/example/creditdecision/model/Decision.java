package com.example.creditdecision.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Decision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private Double rendaMensal;

    private Integer score;

    private String resultado;

    private Double limite;
}
