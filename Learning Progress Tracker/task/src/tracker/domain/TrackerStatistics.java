package tracker.domain;

import tracker.model.Assignment;
import tracker.model.Course;
import tracker.model.Student;
import tracker.search.CourseGroupSearch;
import tracker.search.CourseStrengthSearch;
import tracker.search.SearchContext;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class TrackerStatistics extends Statistic {

    private final SearchContext searchContext = new SearchContext();
    private final Map<Long, Student> studentMap;
    private final List<Assignment> assignments;
    private final Map<String, Long> courseSubmission;

    public TrackerStatistics(Map<Long, Student> studentMap, List<Assignment> assignments) {
        this.studentMap = studentMap;
        this.assignments = assignments;
        this.courseSubmission = getCourseSubmission();
    }

    /**
     * Establish top learners for given course.
     * @param type {@link CourseType}
     */
    public void showTopLearners(CourseType type) {
        String course = type.capitalize();
        System.out.printf("%s\nid\tpoints\tcompleted\n", course);

        studentMap.values()
                .stream()
                .filter(student -> student.isEnrolled(course))
                .sorted(comparing(student -> sumPoints(student, course), reverseOrder()))
                .map(student -> completion(student, course))
                .forEachOrdered(System.out::print);
    }

    /**
     * Find out which courses are the most popular ones.
     * The most popular has the biggest number of enrolled students.
     * @return List containing names of most popular courses
     */
    @Override
    public List<String> findMostPopularCourses() {
        return searchContext.setStrategy(new CourseGroupSearch(findEnrolledStudentPerCourse())).findMax();
    }

    /**
     * Find out which courses are the least popular ones.
     * @return List containing names of the least popular courses
     */
    @Override
    public List<String> findLeastPopularCourse() {
        return searchContext.setStrategy(new CourseGroupSearch(findEnrolledStudentPerCourse())).findMin();
    }

    /**
     * Establish the hardest course.
     * The easiest course has the lowest average grade per assignment.
     */
    @Override
    public List<String> findHardestCourse() {
        return searchContext.setStrategy(new CourseStrengthSearch(findAverageGradPerAssignmentEachCourse())).findMin();
    }

    /**
     * Establish the easiest course.
     * The easiest course has the highest average grade per assignment.
     */
    @Override
    public List<String> findEasiestCourse() {
        return searchContext.setStrategy(new CourseStrengthSearch(findAverageGradPerAssignmentEachCourse())).findMax();
    }

    /**
     * Find out which course has the highest student activity.
     * Higher student activity means a bigger number of completed tasks;
     * @return List containing names of course with the highest activity
     */
    @Override
    public List<String> findCourseWithHighestActivity() {
        return searchContext.setStrategy(new CourseGroupSearch(courseSubmission)).findMax();
    }


    /**
     * Find out which course has the lowest student activity.
     * @return List containing names of course with the lowest activity
     */
    @Override
    public List<String> findCourseWithLowestActivity() {
        return searchContext.setStrategy(new CourseGroupSearch(courseSubmission)).findMin();
    }

    private Map<String, Long> getCourseSubmission() {
        return assignments.stream()
                .collect(groupingBy(assignment -> assignment.getCourse().getName(), counting()));
    }

    private Map<String, Double> findAverageGradPerAssignmentEachCourse() {

        Map<String, Double> averageGradMap = new ConcurrentHashMap<>();
        CourseType.names().forEach(s -> {
            double avgGrad = average(s);
            if (avgGrad > 0) {
                averageGradMap.put(s, avgGrad);
            }
        });

        return averageGradMap;
    }

    private double average(String courseName) {
        return assignments.stream()
                .map(Assignment::getCourse)
                .filter(course -> courseName.equalsIgnoreCase(course.getName()))
                .map(Course::getPoints)
                .collect(Collectors.averagingDouble(Long::doubleValue));
    }

    private Map<String, Long> findEnrolledStudentPerCourse() {
        Map<String, Long> enrolledStudentPerCourse = new ConcurrentHashMap<>();

        for (String course : CourseType.names()) {
            for (Student student : studentMap.values()) {
                if (student.isEnrolled(course)) {
                    enrolledStudentPerCourse.put(course, enrolledStudentPerCourse.getOrDefault(course, 0L) + 1);
                }
            }
        }

        return enrolledStudentPerCourse;
    }
}
