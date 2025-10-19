package com.example.demo.repositories;

import com.example.demo.entities.Operators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operators, Long> {
}
