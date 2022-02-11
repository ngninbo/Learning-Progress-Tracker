package tracker.utils;

import tracker.builder.StudentBuilder;
import tracker.builder.TrackerAnalyserBuilder;
import tracker.model.Assignment;
import tracker.model.Course;
import tracker.model.Student;
import tracker.statistics.Statistic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrackerHelperFunction extends TrackerUtil {

    public static String stringifyProgressData(Student student) {
        StringBuilder progressData = new StringBuilder(String.format(TrackerUtil.STUDENT_DETAIL_TEXT, student.getId()));
        student.getCourses().forEach((s, course) -> {
            String format = BASE_COURSES.get(3).equals(s) ? "%s=%d" : "%s=%d; ";
            progressData.append(String.format(format, s, course.getPoints()));
        });
        return progressData.toString();
    }

    public static Long getMaxPoints(String course) {
        long maxPoint = 0;
        switch (course) {
            case "Java":
                maxPoint = TrackerUtil.MAX_POINTS_JAVA;
                break;
            case "DSA":
                maxPoint = TrackerUtil.MAX_POINTS_DSA;
                break;
            case "Databases":
                maxPoint = TrackerUtil.MAX_POINTS_DATABASES;
                break;
            case "Spring":
                maxPoint = TrackerUtil.MAX_POINTS_SPRING;
                break;
        }
        return maxPoint;
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

    /**
     * This method is only for test purpose
     *
     * @param submissionData Test data
     * @return Statistic class object
     */
    public static Statistic initStatistic(List<List<Long>> submissionData) {
        Map<Long, Student> studentMap = new ConcurrentHashMap<>();
        Map<String, Long> initialSubmission = initialSubmission();
        List<Assignment> assignments = new ArrayList<>();

        submissionData.forEach(list -> {
            long id = list.get(0);
            Student student;
            if (studentMap.containsKey(id)) {
                student = studentMap.get(id);
            } else {
                student = StudentBuilder.init()
                        .withId(id)
                        .withCourses()
                        .build();
            }

            IntStream.range(1, list.size())
                    .forEach(i -> {
                        String name = BASE_COURSES.get(i - 1);
                        Long points = list.get(i);
                        student.updateCourse(name, points);
                        if (points > 0) {
                            long count = initialSubmission.get(name) + 1;
                            initialSubmission.put(name, count);
                            Assignment assignment = new Assignment(id, new Course(name, points));
                            assignments.add(assignment);
                        }
                    });

            studentMap.put(id, student);
        });

        return TrackerAnalyserBuilder.init()
                .withStudentMap(studentMap)
                .withCourseSubmissions(initialSubmission)
                .withAssignmentList(assignments)
                .withFinder()
                .withSearchContext()
                .withStrengthSearchContext()
                .build();
    }
}
