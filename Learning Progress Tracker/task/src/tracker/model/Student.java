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

    public String getFullName() {
        return String.format("%s %s", firstname, lastname);
    }

    public Long maxPoints(String courseName) {
        return courses.get(courseName).getMaxPoints();
    }
}
