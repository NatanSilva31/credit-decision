package com.example.creditdecision.controller;

import com.example.creditdecision.dto.DecisionRequest;
import com.example.creditdecision.dto.DecisionResponse;
import com.example.creditdecision.model.Decision;
import com.example.creditdecision.service.DecisionService;

// Imports de Documentação (Swagger) e Validação
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decision")
@RequiredArgsConstructor // 1. Injeção segura via construtor (Lombok)
@Tag(name = "Análise de Crédito", description = "Endpoints para cálculo de risco e consulta de histórico") // 2. Título no Swagger
public class DecisionController {

    private final DecisionService service; // 'final' obriga o Spring a injetar a dependência

    @PostMapping
    @Operation(summary = "Realizar nova análise", description = "Recebe CPF, Renda e Score para definir aprovação e limite")
    public ResponseEntity<DecisionResponse> analyze(@RequestBody @Valid DecisionRequest request){
        // O @Valid ativa as regras do DTO (CPF, Score > 0, etc).
        // Se falhar, nem entra aqui (vai para o GlobalExceptionHandler).
        return ResponseEntity.ok(service.process(request));
    }

    @GetMapping
    @Operation(summary = "Listar histórico", description = "Retorna todas as análises salvas no banco de dados")
    public ResponseEntity<List<Decision>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
}