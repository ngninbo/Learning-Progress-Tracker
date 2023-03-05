package tracker.command;

import tracker.domain.NotificationService;
import tracker.model.Student;

import java.util.Map;

public class TrackerNotifyCommand implements Command {

    public Map<Long, Student> students;

    public TrackerNotifyCommand(Map<Long, Student> students) {
        this.students = students;
    }

    @Override
    public void execute() {
        sendNotification();
    }

    private void sendNotification() {
        new NotificationService().sendNotification(students.values());
    }
}
