package tracker.util;

import tracker.model.Assignment;
import tracker.model.Course;
import tracker.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TrackerUtil {

    public static final String JAVA = "Java";
    public static final String DSA = "DSA";
    public static final String DATABASES = "Databases";
    public static final String SPRING = "Spring";
    public static final long MAX_POINTS_JAVA = 600;
    public static final long MAX_POINTS_DSA = 400;
    public static final long MAX_POINTS_DATABASES = 480;
    public static final long MAX_POINTS_SPRING = 550;

    public static final String NOTIFICATION_MSG_FORMAT = "To: %s\n" +
            "Re: Your Learning Progress\n" +
            "Hello, %s! You have accomplished our %s course!\n";
    public static final String NOTIFICATION_SUCCEED_MSG = "Total %s students have been notified.\n";

    public static final List<String> BASE_COURSES = List.of(
            JAVA,
            DSA,
            DATABASES,
            SPRING
    );

    public static final List<String> STATISTICS_ROWS_NAMES = List.of(
            "Most popular",
            "Least popular",
            "Highest activity",
            "Lowest activity",
            "Easiest course",
            "Hardest course"
    );
    public static final String BACK_COMMAND = "back";


    public static String stringifyCourseList(List<String> courses) {
        StringBuilder sb;
        sb = new StringBuilder();
        for (int i = 0; i < courses.size(); i++) {
            sb.append(courses.get(i));
            if (i < courses.size() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
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

    public static String printCoursePoints(Student student) {
        StringBuilder sb = new StringBuilder(String.format("%s points: ", student.getId()));
        student.getCourses().forEach((s, course) -> {
            String format = SPRING.equals(s) ? "%s=%d" : "%s=%d; ";
            sb.append(String.format(format, s, course.getPoints()));
        });
        return sb.toString();
    }

    public static long maxPoints(String name) {
        switch (name) {
            case JAVA:
                return MAX_POINTS_JAVA;
            case DATABASES:
                return MAX_POINTS_DATABASES;
            case DSA:
                return MAX_POINTS_DSA;
            case SPRING:
                return MAX_POINTS_SPRING;
            default:
                return 0;
        }
    }

    public static String requestUserInput() {
        return new Scanner(System.in).nextLine();
    }

    public static void logInfoForIncorrectValue(String value) {
        System.out.printf("Incorrect %s.\n", value);
    }
}
