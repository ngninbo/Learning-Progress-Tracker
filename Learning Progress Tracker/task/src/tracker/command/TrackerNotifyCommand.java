package tracker.command;

import tracker.Tracker;
import tracker.model.Course;
import tracker.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static tracker.util.TrackerUtil.BASE_COURSES;
import static tracker.util.TrackerUtil.NOTIFICATION_SUCCEED_MSG;

public class TrackerNotifyCommand implements Command {

    @Override
    public void execute() {
        sendNotification();
    }

    private void sendNotification() {
        List<Long> notifiedStudent = new ArrayList<>();

        BASE_COURSES.forEach(name -> Tracker.students.values().forEach(student -> {

            Map<String, Course> courses = student.getCourses();
            if (courses.containsKey(name) && !courses.get(name).isNotify()) {
                Student tmp = student.sendNotification(name);
                if (tmp.isNotifyFor(name)) {
                    notifiedStudent.add(student.getId());
                    Tracker.students.replace(tmp.getId(), tmp);
                }
            }

        }));

        long numberNotifiedStudent = notifiedStudent.stream().distinct().count();

        System.out.printf(NOTIFICATION_SUCCEED_MSG, numberNotifiedStudent);
    }
}
