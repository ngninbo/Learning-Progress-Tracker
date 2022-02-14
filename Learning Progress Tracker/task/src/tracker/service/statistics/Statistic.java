package tracker.service.statistics;

import tracker.model.Assignment;
import tracker.model.Student;

import java.util.List;
import java.util.Map;

public interface Statistic {

    List<String> findMostPopularCourses();
    List<String> findLeastPopularCourse();
    List<String> findHardestCourse();
    List<String> findEasiestCourse();
    List<String> findCourseWithHighestActivity();
    List<String> findCourseWithLowestActivity();
    void showCourseDetails(String courseName);
    Statistic setStudentMap(Map<Long, Student> studentMap);
    Statistic setCourseSubmission(Map<String, Long> courseSubmission);
    void setAssignments(List<Assignment> assignments);
    String findCourseByCategory(String category);
}
