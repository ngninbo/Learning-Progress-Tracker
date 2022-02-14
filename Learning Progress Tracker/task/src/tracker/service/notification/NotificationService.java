package tracker.service.notification;

import tracker.model.Student;

import java.util.Map;

public interface NotificationService {

    Map<Long, Student> sendNotificationMessage();
    NotificationService setStudents(Map<Long, Student> studentMap);
}
