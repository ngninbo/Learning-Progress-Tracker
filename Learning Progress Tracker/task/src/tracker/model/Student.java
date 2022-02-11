package tracker.model;

import tracker.utils.TrackerUtil;
import tracker.utils.Validator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Student {

    private Long id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private Map<String, Course> courses;

    public Student(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public Student(Long id, String firstname, String lastname, String email) {
        this(firstname, lastname, email);
        this.id = id;
    }

    public Student(Long id, String firstname, String lastname, String email,
                   Map<String, Course> courses) {
       this(id, firstname, lastname, email);
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, Course> getCourses() {
        return courses;
    }

    public boolean hasValidFirstName() {
        return Validator.isValidName(firstname);
    }
    
    public boolean hasValidLastName() {
        return Validator.isValidLastname(lastname);
    }
    
    public boolean hasValidEmail() {
        return Validator.isValidEmail(email);
    }
    
    public boolean isValid() {
        return hasValidEmail() && hasValidFirstName() && hasValidLastName();
    }

    public boolean isEnrolled(String course) {
        return courses.get(course).isEnrolled();
    }

    public Long sumPoints(String courseName) {
        return courses.get(courseName).getPoints();
    }

    public boolean hasCompleted(String courseName) {
        Course course = courses.get(courseName);
        return course.isCompleted();
    }

    public double progress(String courseName) {
        final Course course = courses.get(courseName);
        return new BigDecimal((double) sumPoints(courseName) * TrackerUtil.PERCENT / course.getMaxPoints())
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public String getFullName() {
        return String.format("%s %s", firstname, lastname);
    }

    public Student sendNotification(String courseName) {
        Course course = courses.get(courseName);
        if (course.isCompleted() && !course.isNotify()) {
            Course tmp = course.sendNotification(this);
            courses.replace(courseName, tmp);
        }
        return this;
    }

    public boolean isNotifyFor(String courseName) {
        return courses.get(courseName).isNotify();
    }

    public void updateCourse(String name, long points) {

        if (courses.containsKey(name)) {
            Course course = courses.get(name);
            course.updatePoints(points);
            courses.replace(name, course);
        } else {
            courses.put(name, new Course(name, points));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId()) &&
                Objects.equals(getFirstname(), student.getFirstname()) &&
                Objects.equals(getLastname(), student.getLastname()) &&
                Objects.equals(getEmail(), student.getEmail()) &&
                Objects.equals(getCourses(), student.getCourses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getEmail(), getCourses());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + courses +
                '}';
    }

}
