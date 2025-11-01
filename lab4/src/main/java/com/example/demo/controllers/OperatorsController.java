package com.example.demo.controllers;

import com.example.demo.entities.Operators;
import com.example.demo.services.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operators")
public class OperatorsController {

    private final OperatorService operatorService;

    @GetMapping
    public ResponseEntity<?> getAllOperators() {
        List<Operators> operators = operatorService.getAllOperators();

        if (operators.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(operators, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> addOperator(@RequestBody Operators operator) {
        Operators createdOperator = operatorService.addOperator(operator);
        return new ResponseEntity<>(createdOperator, HttpStatus.CREATED);
    }

    @PutMapping("/{operatorId}/assign/{requestId}")
    public ResponseEntity<?> assignOperatorToRequest(@PathVariable Long operatorId,
                                                     @PathVariable Long requestId) {
        Object updatedRequest = operatorService.assignOperatorToRequest(operatorId, requestId);

        if (Objects.isNull(updatedRequest)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(updatedRequest);
        }
    }
}