package tracker.model;

import lombok.*;

import java.util.Map;

@Builder
@Data
public class Student {

    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private Map<String, Course> courses;

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
