package tracker.model;

import java.util.Objects;

import static tracker.utils.TrackerUtil.*;

public class Course {

    private final String name;
    private Long points;
    private final Long maxPoints;
    private boolean isNotify;

    public Course(String name) {
        this.name = name;
        this.maxPoints = getMaxPoints(name);
    }

    public Course(String name, long points) {
        this(name);
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public Long getPoints() {
        return points;
    }

    public void updatePoints(long points) {
        this.points += points;
    }

    public boolean isEnrolled() {
        return points > 0;
    }

    public Long getMaxPoints() {
        return maxPoints;
    }

    public boolean isCompleted() {
        return Objects.equals(points, maxPoints);
    }

    public boolean isNotify() {
        return isNotify;
    }

    public Course sendNotification(Student student) {
        if (student.hasCompleted(name) && !isNotify()) {
            System.out.printf(NOTIFICATION_MSG_FORMAT, student.getEmail(), student.getFullName(), name);
            this.isNotify = true;
        }
        return this;
    }

    public static Long getMaxPoints(String course) {
        switch (course) {
            case JAVA:
                return MAX_POINTS_JAVA;
            case DSA:
                return MAX_POINTS_DSA;
            case DATABASES:
                return MAX_POINTS_DATABASES;
            case SPRING:
                return MAX_POINTS_SPRING;
            default:
                return 0L;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(getName(), course.getName()) && Objects.equals(getPoints(), course.getPoints()) &&
                Objects.equals(getMaxPoints(), course.getMaxPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPoints(), getMaxPoints());
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}
