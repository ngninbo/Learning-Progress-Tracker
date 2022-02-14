package tracker.service;

import tracker.model.Student;

import java.util.List;
import java.util.Map;

public interface TrackerService {

    Map<Long, Student> getStudentMap();
    void addStudents();
    boolean validateCredentials(Student student);
    boolean isEmailTaken(String email);
    void sendNotificationMessage();
    void addPoints();
    void showOverview();
    void showStatistics();
    void findStudent();
    boolean updateStudentCoursePoints(List<Long> progressData);
    boolean isStudentNotMatch(long id);
}
