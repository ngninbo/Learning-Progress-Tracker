package tracker.model;

import lombok.*;

import java.util.Map;
import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(firstname, student.firstname) &&
                Objects.equals(lastname, student.lastname) && Objects.equals(email, student.email) &&
                Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
