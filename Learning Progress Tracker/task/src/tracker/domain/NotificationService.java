package tracker.domain;

import tracker.model.Course;
import tracker.model.CourseStatus;
import tracker.model.Student;

import java.util.*;
import java.util.function.Predicate;

public class NotificationService {

    private static final String NOTIFICATION_MSG_FORMAT = "To: %s\n" +
            "Re: Your Learning Progress\n" +
            "Hello, %s! You have accomplished our %s course!\n";

    private static final String NOTIFICATION_SUCCEED_MSG = "Total %s students have been notified.\n";

    public void sendNotification(Collection<Student> students) {

        List<Student> notifiedStudent = new ArrayList<>();

        for (Student student : students) {
            for (Course course : student.getCourses().values()) {
                if (isNotificationPending().test(course)) {
                    sendNotification(student, course);
                    notifiedStudent.add(student);
                }
            }
        }

        long numberNotifiedStudent = notifiedStudent.stream().distinct().count();

        System.out.printf(NOTIFICATION_SUCCEED_MSG, numberNotifiedStudent);
    }

    private void sendNotification(Student student, Course course) {
        System.out.printf(NOTIFICATION_MSG_FORMAT, student.getEmail(), student.getFullName(), course.getName());
        course.updateStatus(CourseStatus.NOTIFIED);
    }

    private Predicate<Course> isCompleted() {
        return course -> course.getStatuses().contains(CourseStatus.COMPLETED);
    }

    private Predicate<Course> isNotified() {
        return course -> course.getStatuses().contains(CourseStatus.NOTIFIED);
    }

    private Predicate<Course> isNotificationPending() {
        return course -> isCompleted().and(isNotified().negate()).test(course);
    }
}
