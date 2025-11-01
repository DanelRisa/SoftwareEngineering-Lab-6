package com.example.demo.controllers;

import com.example.demo.entities.ApplicationRequest;
import com.example.demo.services.AppReqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/requests")
public class ApplicationRequestController {

    private final AppReqService appReqService;

    @GetMapping
    public ResponseEntity<?> getAllRequests() {
        List<ApplicationRequest> requests = appReqService.getAllReq();

        if (requests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(requests, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRequestById(@PathVariable Long id) {
        ApplicationRequest request = appReqService.getReqById(id);

        if (Objects.isNull(request)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(request);
        }
    }

    @PostMapping
    public ResponseEntity<?> addRequest(@RequestBody ApplicationRequest request) {
        ApplicationRequest createdRequest = appReqService.addRequest(request);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRequest(@PathVariable Long id,
                                           @RequestBody ApplicationRequest request) {
        ApplicationRequest updatedRequest = appReqService.updateRequest(id, request);

        if (Objects.isNull(updatedRequest)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(updatedRequest);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable Long id) {
        boolean deleted = appReqService.deleteReq(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}