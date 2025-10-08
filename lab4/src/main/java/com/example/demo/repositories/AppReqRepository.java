package com.example.demo.repositories;

import com.example.demo.entities.ApplicationRequest;
import jakarta.transaction.Transactional;
import org.apache.coyote.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Transactional
public interface AppReqRepository extends JpaRepository<ApplicationRequest, Long> {
    List<ApplicationRequest> findByHandled(boolean handled);

}
