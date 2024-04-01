package com.FirstApi.restapi.services;

import com.FirstApi.restapi.databaseImpl.CourseDao;
import com.FirstApi.restapi.entities.Courses;
import com.FirstApi.restapi.requests.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseSericeImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public Courses createCourse(CourseRequest courseRequest) {
        Courses courses = new Courses();
        courses.setName(courseRequest.getCourseName());
        courses.setDescription(courseRequest.getDescription());
         return courseDao.createCourse(courses);
    }


    @Override
    public void deleteCourses(int courseId) {
        CourseDao.deleteCourse(courseId);
    }

    @Override
    public List<Courses> detailsCourses() {
        return courseDao.detailsCourses();

    }

    @Override
    public Courses getCoursesById(int courseId){
        return courseDao.getCourseById(courseId);
    }

    @Override
    public void updateCourse(Courses course) {
       courseDao.updateCourse(course);
    }


}
