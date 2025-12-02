
package com.example.creditdecision.controller;

import com.example.creditdecision.dto.DecisionRequest;
import com.example.creditdecision.dto.DecisionResponse;
import com.example.creditdecision.service.DecisionService;
import com.example.creditdecision.model.Decision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decision")
public class DecisionController {

    @Autowired
    private DecisionService service;

    @PostMapping
    public ResponseEntity<DecisionResponse> analyze(@RequestBody DecisionRequest request){
        return ResponseEntity.ok(service.process(request));
    }

    @GetMapping
    public ResponseEntity<List<Decision>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
}
