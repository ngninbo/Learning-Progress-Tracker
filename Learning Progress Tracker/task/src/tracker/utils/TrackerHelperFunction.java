package tracker.utils;

import tracker.model.Assignment;
import tracker.model.Course;
import tracker.model.Student;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TrackerHelperFunction extends TrackerUtil {

    public static String stringifyProgressData(Student student) {
        StringBuilder progressData = new StringBuilder(String.format(TrackerUtil.STUDENT_DETAIL_TEXT, student.getId()));
        student.getCourses().forEach((s, course) -> {
            String format = BASE_COURSES.get(3).equals(s) ? "%s=%d" : "%s=%d; ";
            progressData.append(String.format(format, s, course.getPoints()));
        });
        return progressData.toString();
    }

    public static Map<String, Long> initialSubmission() {
        return BASE_COURSES.stream()
                .collect(Collectors.toMap(s -> s, s -> 0L, (a, b) -> b));
    }

    public static Map<String, Long> findEnrolledStudentPerCourse(Map<Long, Student> studentMap) {
        Map<String, Long> enrolledStudentPerCourse = new ConcurrentHashMap<>();

        if (studentMap.isEmpty()) {
            return Map.of();
        }

        BASE_COURSES.forEach(course -> {
            long enrollment = studentMap.values()
                    .stream()
                    .filter(student -> student.isEnrolled(course))
                    .count();

            enrolledStudentPerCourse.put(course, enrollment);
        });

        return enrolledStudentPerCourse;
    }

    public static Map<String, Double> findAverageGradPerAssignmentEachCourse(List<Assignment> assignments) {

        Map<String, Double> averageGradMap = new ConcurrentHashMap<>();
        BASE_COURSES.forEach(s -> {
            double avgGrad = assignments.stream()
                    .map(Assignment::getCourse)
                    .filter(course -> s.equals(course.getName()))
                    .map(Course::getPoints)
                    .collect(Collectors.averagingDouble(Long::doubleValue));

            if (avgGrad > 0) {
                averageGradMap.put(s, avgGrad);
            }
        });

        return averageGradMap;
    }
}
