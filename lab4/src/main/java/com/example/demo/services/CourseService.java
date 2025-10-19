package com.example.demo.services;

import com.example.demo.entities.Courses;
import com.example.demo.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }


    public Courses getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }
}