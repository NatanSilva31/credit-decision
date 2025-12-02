package com.example.creditdecision.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Diz ao Spring: "Eu sou o Gerente que resolve problemas de todos os Controllers"
public class GlobalExceptionHandler {

    // Esse método captura erros de validação (quando o @Valid falha no DTO)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        
        Map<String, String> errors = new HashMap<>();
        
        // Pega cada campo que deu erro (ex: "cpf", "score") e a mensagem ("CPF inválido")
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        
        // Retorna um JSON limpo com erro 400 (Bad Request)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}