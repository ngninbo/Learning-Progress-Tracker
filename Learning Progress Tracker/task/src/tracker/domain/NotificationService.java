package tracker.domain;

import tracker.model.Course;
import tracker.model.Student;

import java.util.HashSet;
import java.util.Collection;
import java.util.Set;

public class NotificationService {

    private static final String NOTIFICATION_MSG_FORMAT = "To: %s\n" +
            "Re: Your Learning Progress\n" +
            "Hello, %s! You have accomplished our %s course!\n";

    private static final String NOTIFICATION_SUCCEED_MSG = "Total %s students have been notified.\n";

    public void sendNotification(Collection<Student> students) {

        Set<Student> notifiedStudent = new HashSet<>();

        for (Student student : students) {
            for (Course course : student.getCourses().values()) {
                if (course.isCompleted() && !course.isNotified()) {
                    sendNotification(student, course);
                    notifiedStudent.add(student);
                }
            }
        }

        System.out.printf(NOTIFICATION_SUCCEED_MSG, notifiedStudent.size());
    }

    private void sendNotification(Student student, Course course) {
        System.out.printf(NOTIFICATION_MSG_FORMAT, student.getEmail(), student.getFullName(), course.getName());
        course.setNotified(true);
    }
}
