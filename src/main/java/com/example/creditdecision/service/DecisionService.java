
package com.example.creditdecision.service;

import com.example.creditdecision.dto.DecisionRequest;
import com.example.creditdecision.dto.DecisionResponse;
import com.example.creditdecision.model.Decision;
import com.example.creditdecision.repository.DecisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecisionService {

    @Autowired
    private DecisionRepository repository;

    public DecisionResponse process(DecisionRequest request) {

        String resultado;
        Double limite;

        if (request.score < 400){
            resultado = "NEGADO";
            limite = 0.0;
        } else if (request.score < 700){
            resultado = "APROVADO";
            limite = 1000.0;
        } else {
            resultado = "APROVADO";
            limite = 2000.0;
        }

        Decision decision = new Decision();
        decision.setCpf(request.cpf);
        decision.setRendaMensal(request.rendaMensal);
        decision.setScore(request.score);
        decision.setResultado(resultado);
        decision.setLimite(limite);

        repository.save(decision);

        DecisionResponse response = new DecisionResponse();
        response.cpf = request.cpf;
        response.resultado = resultado;
        response.limite = limite;

        return response;
    }

    public List<Decision> getAll() {
        return repository.findAll();
    }
}
