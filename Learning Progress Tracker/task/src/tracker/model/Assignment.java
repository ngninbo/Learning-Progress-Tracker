package tracker.model;

import java.util.Objects;

public class Assignment {

    private final Long studentId;
    private final Course course;

    public Assignment(Long studentId, Course course) {
        this.studentId = studentId;
        this.course = course;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assignment)) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(getStudentId(), that.getStudentId()) && Objects.equals(getCourse(), that.getCourse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getCourse());
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "studentId=" + studentId +
                ", course=" + course +
                '}';
    }
}
