package tracker.notification;

import tracker.model.Course;
import tracker.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static tracker.utils.TrackerUtil.*;

public class NotificationServiceImpl implements NotificationService {

    private Map<Long, Student> students;

    public NotificationService setStudents(Map<Long, Student> students) {
        this.students = students;
        return this;
    }

    @Override
    public Map<Long, Student> sendNotificationMessage() {

        List<Long> notifiedStudent = new ArrayList<>();

        BASE_COURSES.forEach(course -> students.values().forEach(student1 -> {

            Map<String, Course> courses = student1.getCourses();
            if (courses.containsKey(course) && !courses.get(course).isNotify()) {
                Student student = student1.sendNotification(course);
                if (student.isNotifyFor(course)) {
                    notifiedStudent.add(student1.getId());
                    students.replace(student.getId(), student);
                }
            }

        }));

        long numberNotifiedStudent =  notifiedStudent.stream().distinct().count();

        System.out.printf(NOTIFICATION_SUCCEED_MSG, numberNotifiedStudent);

        return students;
    }
}
