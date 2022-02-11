package tracker.model;

import tracker.utils.TrackerUtil;

import java.util.Objects;

import static tracker.utils.TrackerUtil.NOTIFICATION_MSG_FORMAT;

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
        long maxPoint = 0;
        switch (course) {
            case "Java":
                maxPoint = TrackerUtil.MAX_POINTS_JAVA;
                break;
            case "DSA":
                maxPoint = TrackerUtil.MAX_POINTS_DSA;
                break;
            case "Databases":
                maxPoint = TrackerUtil.MAX_POINTS_DATABASES;
                break;
            case "Spring":
                maxPoint = TrackerUtil.MAX_POINTS_SPRING;
                break;
        }
        return maxPoint;
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
