package com.FirstApi.restapi.services;

import com.FirstApi.restapi.entities.Courses;
import com.FirstApi.restapi.requests.CourseRequest;

import java.util.List;

public interface CourseService {


    Courses createCourse(CourseRequest courseRequest);

    void deleteCourses(int courseId);

    List<Courses> detailsCourses();

    Courses getCoursesById(int courseId);

    void updateCourse(Courses course);
}
