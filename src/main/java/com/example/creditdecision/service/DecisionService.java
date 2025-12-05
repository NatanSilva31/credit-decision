package com.example.creditdecision.service;

import com.example.creditdecision.dto.DecisionRequest;
import com.example.creditdecision.dto.DecisionResponse;
import com.example.creditdecision.model.Decision;
import com.example.creditdecision.repository.DecisionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DecisionService {

    private final DecisionRepository repository;

    @Transactional
    public DecisionResponse process(DecisionRequest request) {

        log.info("Iniciando análise de crédito para CPF: {}", request.getCpf());

        String resultado;
        Double limite;
        String mensagem;

        // ======= Lógica de Score =======
        if (request.getScore() < 400) {
            resultado = "NEGADO";
            limite = 0.0;
            mensagem = "Solicitação negada. O score informado está abaixo do mínimo permitido.";
        }
        else if (request.getScore() < 700) {
            resultado = "APROVADO";
            limite = 1000.0;
            mensagem = "Aprovado com limite básico de R$ 1.000.";
        }
        else {
            resultado = "APROVADO";
            limite = 2000.0;
            mensagem = "Aprovado! Limite premium disponível de R$ 2.000.";
        }

        log.info("Resultado da analise: {} | Score: {} | Limite: {}", resultado, request.getScore(), limite);

        // ======= Salvar no banco =======
        Decision decision = new Decision();
        decision.setCpf(request.getCpf());
        decision.setRendaMensal(request.getRendaMensal());
        decision.setScore(request.getScore());
        decision.setResultado(resultado);
        decision.setLimite(limite);

        repository.save(decision);
        log.info("Decisão registrada no banco com ID: {}", decision.getId());

        // ======= Retorno para API =======
        DecisionResponse response = new DecisionResponse();
        response.setCpf(request.getCpf());
        response.setResultado(resultado);
        response.setLimite(limite);
        response.setMensagem(mensagem);

        return response;
    }

    public List<Decision> getAll() {
        return repository.findAll();
    }
}
