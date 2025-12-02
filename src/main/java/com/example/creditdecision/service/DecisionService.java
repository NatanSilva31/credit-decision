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
@Slf4j // 1. Habilita logs para auditoria
@RequiredArgsConstructor // 2. Cria o construtor automaticamente para injeção segura
public class DecisionService {

    private final DecisionRepository repository; // 'final' torna a dependência obrigatória

    @Transactional // 3. Abre uma transação no banco de dados
    public DecisionResponse process(DecisionRequest request) {
        // Log de entrada
        log.info("Iniciando análise de crédito para o CPF: {}", request.getCpf());

        String resultado;
        Double limite;

        // Lógica de Negócio
        if (request.getScore() < 400){
            resultado = "NEGADO";
            limite = 0.0;
        } else if (request.getScore() < 700){
            resultado = "APROVADO";
            limite = 1000.0;
        } else {
            resultado = "APROVADO";
            limite = 2000.0;
        }

        // Log de processamento
        log.info("Score {} processado. Resultado: {}", request.getScore(), resultado);

        // Montagem da Entidade para salvar no banco
        Decision decision = new Decision();
        decision.setCpf(request.getCpf());
        decision.setRendaMensal(request.getRendaMensal());
        decision.setScore(request.getScore());
        decision.setResultado(resultado);
        decision.setLimite(limite);

        repository.save(decision);
        log.info("Decisão salva no banco com ID: {}", decision.getId());

        // Montagem da Resposta para a API
        DecisionResponse response = new DecisionResponse();
        response.setCpf(request.getCpf());
        response.setResultado(resultado);
        response.setLimite(limite);

        return response;
    }

    public List<Decision> getAll() {
        return repository.findAll();
    }
}