package tracker.builder;

import tracker.model.Course;
import tracker.model.Student;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StudentBuilder {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Map<String, Course> courses;

    private StudentBuilder() {
    }

    public static StudentBuilder init() {
        return new StudentBuilder();
    }

    public StudentBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public StudentBuilder withCredentials(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        return this;
    }

    public StudentBuilder withCourses() {
        this.courses = new ConcurrentHashMap<>();
        return this;
    }

    public Student build() {
        return new Student(id, firstname, lastname, email, courses);
    }
}
