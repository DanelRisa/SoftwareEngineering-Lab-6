package com.example.demo.services;

import com.example.demo.entities.ApplicationRequest;
import com.example.demo.entities.Operators;
import com.example.demo.repositories.OperatorRepository;
import com.example.demo.repositories.AppReqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatorService {

    private final OperatorRepository operatorRepository;
    private final AppReqRepository appReqRepository;

    public List<Operators> getAllOperators() {
        return operatorRepository.findAll();
    }

    public Operators getOperatorById(Long id) {
        return operatorRepository.findById(id).orElse(null);
    }
    public Operators addOperator(Operators operator) {
        return operatorRepository.save(operator);
    }
    public ApplicationRequest assignOperatorToRequest(Long operatorId, Long requestId) {
        Operators operator = operatorRepository.findById(operatorId).orElse(null);
        ApplicationRequest request = appReqRepository.findById(requestId).orElse(null);

        if (operator == null || request == null) {
            return null;
        }
        if (!request.getOperators().contains(operator)) {
            request.getOperators().add(operator);
            operator.getRequests().add(request);
        }

        return appReqRepository.save(request);
    }
}