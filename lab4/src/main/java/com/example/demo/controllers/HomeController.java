package com.example.demo.controllers;

import com.example.demo.entities.ApplicationRequest;
import com.example.demo.repositories.AppReqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    AppReqRepository appReqRepository;
    @GetMapping(value = "/")
    public String index(Model model) {
        List<ApplicationRequest> requests = appReqRepository.findAll();
        model.addAttribute("requests",  requests);
        return "index";
    }

    @GetMapping(value = "/addrequest")
    public String addrequest_page(Model model) {
        return "addrequest";
    }


    @PostMapping(value = "/addrequest")
    public String addRequest(@RequestParam(name = "username") String userName,
                             @RequestParam(name = "coursename") String courseName,
                             @RequestParam(name = "commentary") String commentary,
                             @RequestParam(name = "phone") String phone) {
        ApplicationRequest request = new ApplicationRequest();
        request.setUserName(userName);
        request.setCourseName(courseName);
        request.setCommentary(commentary);
        request.setPhone(phone);
        request.setHandled(false);

        appReqRepository.save(request);
        return "addrequest";
    }

    @GetMapping(value = "/pending")
    public String pendingRequests(Model model) {
        List<ApplicationRequest> pendingRequests = appReqRepository.findByHandled(false);
        model.addAttribute("requests", pendingRequests);
        return "pending";
    }

    @GetMapping(value = "/processed")
    public String processedRequests(Model model) {
        List<ApplicationRequest> processedRequests = appReqRepository.findByHandled(true);
        model.addAttribute("requests", processedRequests);
        return "processed";
    }
    @GetMapping(value = "/details/{requestId}")
    public String details(Model model, @PathVariable(name = "requestId") Long id) {
        ApplicationRequest request = appReqRepository.findById(id).orElse(null);
        model.addAttribute("request", request);
        return "details";
    }

    @PostMapping(value = "/saverequest")
    public String saveRequest(@RequestParam(name = "id") Long id,
                              @RequestParam(name = "username") String userName,
                              @RequestParam(name = "coursename") String courseName,
                              @RequestParam(name = "commentary") String commentary,
                              @RequestParam(name = "phone") String phone,
                              @RequestParam(name = "handled") boolean handled) {
        ApplicationRequest request = appReqRepository.findById(id).orElse(null);
        if (request != null) {
            request.setUserName(userName);
            request.setCourseName(courseName);
            request.setCommentary(commentary);
            request.setPhone(phone);
            request.setHandled(handled);
            appReqRepository.save(request);
            return "redirect:/details/" + id;
        }
        return "redirect:/";
    }

    @PostMapping(value = "/processrequest")
    public String processRequest(@RequestParam(name = "id") Long id) {
        ApplicationRequest request = appReqRepository.findById(id).orElse(null);
        if (request != null) {
            request.setHandled(true);
            appReqRepository.save(request);
        }
        return "redirect:/details/" + id;
    }

    @PostMapping(value = "/deleterequest")
    public String deleteRequest(@RequestParam(name = "id") Long id) {
        appReqRepository.deleteById(id);
        return "redirect:/";
    }
}
