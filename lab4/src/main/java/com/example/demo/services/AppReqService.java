package com.example.demo.services;

import com.example.demo.entities.ApplicationRequest;
import com.example.demo.entities.Courses;
import com.example.demo.entities.Operators;
import com.example.demo.repositories.AppReqRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppReqService {

    private final AppReqRepository appReqRepository;
    private final CourseService courseService;
    private final OperatorService operatorService;

    public List<ApplicationRequest> getAllReq() {
        return appReqRepository.findAll();
    }

    public ApplicationRequest getReqById(Long id) {
        return appReqRepository.findById(id).orElse(null);
    }

    public List<ApplicationRequest> getPendingReq() {
        return appReqRepository.findByHandled(false);
    }

    public List<ApplicationRequest> getProcessedReq() {
        return appReqRepository.findByHandled(true);
    }

    public void addReq(String userName, Long courseId, String commentary, String phone) {
        ApplicationRequest request = new ApplicationRequest();
        request.setUserName(userName);
        Courses course = courseService.getCourseById(courseId);
        request.setCourse(course);
        request.setCommentary(commentary);
        request.setPhone(phone);
        request.setHandled(false);
        appReqRepository.save(request);
    }

    public ApplicationRequest addRequest(ApplicationRequest request) {
        return appReqRepository.save(request);
    }

    @Transactional
    public void updateReq(Long id, String userName, Long courseId, String commentary,
                          String phone, boolean handled) {
        ApplicationRequest request = getReqById(id);
        if (request != null) {
            request.setUserName(userName);
            Courses course = courseService.getCourseById(courseId);
            request.setCourse(course);
            request.setCommentary(commentary);
            request.setPhone(phone);
            request.setHandled(handled);
            appReqRepository.save(request);
        }
    }

    public ApplicationRequest updateRequest(Long id, ApplicationRequest requestDetails) {
        ApplicationRequest existingRequest = getReqById(id);
        if (existingRequest == null) {
            return null;
        }

        existingRequest.setUserName(requestDetails.getUserName());
        existingRequest.setCommentary(requestDetails.getCommentary());
        existingRequest.setPhone(requestDetails.getPhone());
        existingRequest.setHandled(requestDetails.isHandled());
        if (requestDetails.getCourse() != null) {
            Courses course = courseService.getCourseById(requestDetails.getCourse().getId());
            if (course != null) {
                existingRequest.setCourse(course);
            }
        }

        return appReqRepository.save(existingRequest);
    }

    public boolean deleteReq(Long id) {
        ApplicationRequest request = getReqById(id);
        if (request != null) {
            for (Operators operator : request.getOperators()) {
                operator.getRequests().remove(request);
            }
            request.getOperators().clear();
            appReqRepository.delete(request);
            return true;
        }
        return false;
    }

    public void assignOperators(Long requestId, List<Long> operatorIds) {
        ApplicationRequest request = getReqById(requestId);
        if (request != null && !request.isHandled()) {
            List<Operators> newOperators = new ArrayList<>();
            for (Long opId : operatorIds) {
                Operators operator = operatorService.getOperatorById(opId);
                if (operator != null) {
                    newOperators.add(operator);
                    operator.getRequests().add(request);
                }
            }
            List<Operators> currOp = request.getOperators();
            if (currOp == null) {
                currOp = new ArrayList<>();
            }
            currOp.addAll(newOperators);
            request.setOperators(currOp);
            request.setHandled(true);
            appReqRepository.save(request);
        }
    }

    public void unassignOperator(Long requestId, Long operatorId) {
        ApplicationRequest request = getReqById(requestId);
        if (request != null) {
            Operators operator = operatorService.getOperatorById(operatorId);
            if (operator != null) {
                request.getOperators().remove(operator);
                operator.getRequests().remove(request);
                appReqRepository.save(request);
            }
        }
    }
}