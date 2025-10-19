package com.example.demo.services;

import com.example.demo.entities.Operators;
import com.example.demo.repositories.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatorService {

    private final OperatorRepository operatorRepository;

    public List<Operators> getAllOperators() {
        return operatorRepository.findAll();
    }


    public Operators getOperatorById(Long id) {
        return operatorRepository.findById(id).orElse(null);
    }
}