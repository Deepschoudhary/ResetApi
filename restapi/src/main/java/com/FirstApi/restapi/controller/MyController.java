package com.FirstApi.restapi.controller;

import com.FirstApi.restapi.entities.Courses;
import com.FirstApi.restapi.requests.CourseRequest;
import com.FirstApi.restapi.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {


    @Autowired
    private CourseService courseService;

    @GetMapping("courses")
    public List<Courses> detailsCourses() {
        return this.courseService.detailsCourses();
    }

    @PostMapping
    public Courses createCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.createCourse(courseRequest);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourses(@PathVariable int courseId) {
        courseService.deleteCourses(courseId);
    }

    @GetMapping("courses/{courseId}")
    public Courses getCourseById(@PathVariable int courseId) {
        return courseService.getCoursesById(courseId);
    }


    @PutMapping("/courses/{id}")
    public void updateCourse(@PathVariable int id, @RequestBody Courses course) {
        course.setId(id);
        courseService.updateCourse(course);
    }
}
