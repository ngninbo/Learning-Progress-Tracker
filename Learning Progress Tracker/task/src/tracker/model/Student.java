package tracker.model;

import lombok.*;
import tracker.util.TrackerValidator;

import java.util.Map;

@Builder
@Data
public class Student {

    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private Map<String, Course> courses;

    public boolean hasValidFirstname() {
        return TrackerValidator.validate(firstname);
    }

    public boolean isValid() {
        return hasValidFirstname() & hasValidLastname() & hasValidEmail();
    }

    public boolean hasValidEmail() {
        return TrackerValidator.isValidEmail(email);
    }

    public boolean hasValidLastname() {
        return TrackerValidator.isValidLastname(lastname);
    }

    public void updateCourse(String name, long points) {

        if (courses.containsKey(name)) {
            Course course = courses.get(name);
            course.updatePoints(points);
        } else {
            courses.put(name, new Course(name, points));
        }
    }

    public boolean isEnrolled(String course) {
        return !courses.isEmpty() && courses.get(course).isEnrolled();
    }

    public String getFullName() {
        return String.format("%s %s", firstname, lastname);
    }

    public Long maxPoints(String courseName) {
        return courses.get(courseName).getMaxPoints();
    }
}
