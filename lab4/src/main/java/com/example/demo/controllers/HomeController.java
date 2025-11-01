//package com.example.demo.controllers;
//
//import com.example.demo.entities.ApplicationRequest;
//import com.example.demo.entities.Courses;
//import com.example.demo.entities.Operators;
//import com.example.demo.services.AppReqService;
//import com.example.demo.services.CourseService;
//import com.example.demo.services.OperatorService;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class HomeController {
//
//    private final AppReqService appReqService;
//    private final CourseService courseService;
//    private final OperatorService operatorService;
//
//    @GetMapping(value = "/")
//    public String index(Model model) {
//        List<ApplicationRequest> requests = appReqService.getAllReq();
//
//        model.addAttribute("requests", requests);
//        return "index";
//    }
//
//    @GetMapping(value = "/addrequest")
//    public String addReqPage(Model model) {
//        List<Courses> courses = courseService.getAllCourses();
//        model.addAttribute("courses", courses);
//
//        return "addrequest";
//    }
//
//
//    @PostMapping(value = "/addrequest")
//    public String addReq(
//                            @RequestParam(name = "username") String userName,
//                            @RequestParam(name = "course_id") Long courseId,
//                            @RequestParam(name = "commentary") String commentary,
//                            @RequestParam(name = "phone") String phone) {
//
//        appReqService.addReq(userName, courseId, commentary, phone);
//
//        return "redirect:/";
//    }
//
//
//    @GetMapping(value = "/pending")
//    public String pendingReqs(Model model) {
//        List<ApplicationRequest> pendingRequests = appReqService.getPendingReq();
//        model.addAttribute("requests", pendingRequests);
//
//        return "pending";
//    }
//
//
//    @GetMapping(value = "/processed")
//    public String processedReqs(Model model) {
//        List<ApplicationRequest> processedRequests = appReqService.getProcessedReq();
//
//        model.addAttribute("requests", processedRequests);
//        return "processed";
//    }
//
//
//    @GetMapping(value = "/details/{requestId}")
//    public String details(Model model, @PathVariable(name = "requestId") Long id) {
//        ApplicationRequest request = appReqService.getReqById(id);
//
//        model.addAttribute("request", request);
//
//        List<Courses> courses = courseService.getAllCourses();
//        model.addAttribute("courses", courses);
//        List<Operators> availableOperators = operatorService.getAllOperators();
//
//        if (request.getOperators() != null) {
//            availableOperators.removeAll(request.getOperators());
//        }
//
//        model.addAttribute("available_operators", availableOperators);
//        List<Operators> assignedOperators = request.getOperators();
//        model.addAttribute("assigned_operators", assignedOperators);
//
//        return "details";
//    }
//
////@Transactional
//    @PostMapping(value = "/deleterequest")
//    public String deleteReq(@RequestParam(name = "id") Long id) {
//        appReqService.deleteReq(id);
//
//        return "redirect:/";
//    }
//    @PostMapping(value = "/saverequest")
//    public String saveReq(    @RequestParam(name = "id") Long id,
//                              @RequestParam(name = "username") String userName,
//                              @RequestParam(name = "course_id") Long courseId,
//                              @RequestParam(name = "commentary") String commentary,
//                              @RequestParam(name = "phone") String phone,
//                              @RequestParam(name = "handled") boolean handled) {
//
//        appReqService.updateReq(id, userName, courseId, commentary, phone, handled);
//
//        return "redirect:/details/" + id;
//    }
//
//
//
//    @PostMapping(value = "/assignoperators")
//    public String assignOperators(@RequestParam(name = "request_id") Long requestId,
//                                  @RequestParam(value = "operatorIds", required = false) List<Long> operatorIds) {
//
//        if (operatorIds != null && !operatorIds.isEmpty()) {
//            appReqService.assignOperators(requestId, operatorIds);
//        }
//
//        return "redirect:/details/" + requestId;
//    }
//
//    @PostMapping(value = "/unassignoperator")
//    public String removeOperator(
//                                    @RequestParam(name = "operator_id") Long operatorId,
//                                    @RequestParam(name = "request_id") Long requestId) {
//        appReqService.unassignOperator(requestId, operatorId);
//
//
//        return "redirect:/details/" + requestId;
//    }
//}