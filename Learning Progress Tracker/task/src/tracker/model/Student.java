package tracker.model;

import lombok.*;
import tracker.util.TrackerValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static tracker.util.TrackerUtil.NOTIFICATION_MSG_FORMAT;

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
            courses.replace(name, course);
        } else {
            courses.put(name, new Course(name, points));
        }
    }

    public boolean isEnrolled(String course) {
        return !courses.isEmpty() && courses.get(course).isEnrolled();
    }

    public Long sumPoints(String courseName) {
        return courses.get(courseName).getPoints();
    }

    public String completion(String courseName) {
        return id + "\t" +
                sumPoints(courseName) + "\t" +
                progress(courseName) + "%\n";
    }

    public double progress(String courseName) {
        return BigDecimal.valueOf(sumPoints(courseName))
                .multiply(BigDecimal.TEN.multiply(BigDecimal.TEN))
                .divide(BigDecimal.valueOf(maxPoints(courseName)), 1, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public String getFullName() {
        return String.format("%s %s", firstname, lastname);
    }

    public boolean sendNotification(String courseName) {
        Course course = courses.get(courseName);
        if (course.isCompleted() && !course.isNotified()) {
            System.out.printf(NOTIFICATION_MSG_FORMAT, email, this.getFullName(), courseName);
            course.setNotified(true);
            courses.replace(courseName, course);
            return true;
        }
        return false;
    }

    private Long maxPoints(String courseName) {
        return courses.get(courseName).getMaxPoints();
    }
}
