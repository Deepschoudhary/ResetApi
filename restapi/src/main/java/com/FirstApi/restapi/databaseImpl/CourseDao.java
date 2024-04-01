package com.FirstApi.restapi.databaseImpl;

import com.FirstApi.restapi.connectivity.Connectivity;
import com.FirstApi.restapi.entities.Courses;
import com.FirstApi.restapi.services.CourseSericeImpl;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseDao {

    private static final String INSERT_QUERY = "INSERT INTO courses (name, description) VALUES (?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM courses WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE from courses WHERE id = ?";

    private static final String SELECT_ALL_COURSES = "SELECT * FROM courses";
    private static final String UPDATE_COURSE = "UPDATE courses SET name = ?, description = ? WHERE id = ?";


    public CourseSericeImpl updateCourse;
    private Courses course;


    public  Courses createCourse(Courses courses) {
        try {
            ResultSet generatedKeys = getResultSet(courses);
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                courses.setId(id);
                return getCourseById(id);
            } else {
                throw new SQLException("Creating course failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ResultSet getResultSet(Courses courses) throws SQLException {
        Connection connection = Connectivity.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, courses.getName());
        preparedStatement.setString(2, courses.getDescription());
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected == 0) {
            throw new SQLException("Creating course failed, no rows affected.");
        }

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        return generatedKeys;
    }

    public Courses getCourseById(int courseId) {
        Courses course = null;
        try {
                Connection connection = Connectivity.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);

            preparedStatement.setInt(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String title = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    course= new Courses(courseId, title, description);
                }
                resultSet.close();
                preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }


    public static void deleteCourse(int courseId) {
        try {
            Connection connection = Connectivity.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, courseId);
            boolean isDeleted = preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public List<Courses> detailsCourses() {
        List<Courses> coursesList = new ArrayList<>();

        try {
            Connection connection = Connectivity.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSES);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String title = resultSet.getString("name");
                String description = resultSet.getString("description");
                Courses course = new Courses(id, title, description);
                coursesList.add(course);
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coursesList;
    }

    public void updateCourse(Courses course) {
        try {
            Connection connection = Connectivity.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE);
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setInt(3, course.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }



