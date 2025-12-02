
package com.example.creditdecision.model;

import jakarta.persistence.*;

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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public Double getRendaMensal() { return rendaMensal; }
    public void setRendaMensal(Double rendaMensal) { this.rendaMensal = rendaMensal; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public Double getLimite() { return limite; }
    public void setLimite(Double limite) { this.limite = limite; }
}
