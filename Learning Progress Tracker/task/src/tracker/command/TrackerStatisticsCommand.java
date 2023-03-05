package tracker.command;

import tracker.domain.Category;
import tracker.domain.CourseType;
import tracker.model.Assignment;
import tracker.model.Course;
import tracker.model.Student;
import tracker.search.CourseGroupSearch;
import tracker.search.CourseStrengthSearch;
import tracker.search.SearchContext;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class TrackerStatisticsCommand implements Statistic, Command {

    private final SearchContext searchContext = new SearchContext();
    private final Map<Long, Student> studentMap;
    private final List<Assignment> assignments;
    private final Map<String, Long> courseSubmission;

    public TrackerStatisticsCommand(Map<Long, Student> studentMap, List<Assignment> assignments) {
        this.studentMap = studentMap;
        this.assignments = assignments;
        this.courseSubmission = assignments
                .stream()
                .collect(groupingBy(assignment -> assignment.getCourse().getName(), counting()));
    }

    @Override
    public void execute() {
        stat();
    }

    public void stat() {
        System.out.println("Type the name of a course to see details or 'back' to quit");
        statistics();

        while (true) {
            String courseName = new Scanner(System.in).nextLine();
            if (back().test(courseName)) {
                return;
            } else if (CourseType.noneMatch(courseName)) {
                System.out.println("Unknown course.");
            } else {
                showTopLearners(CourseType.valueOf(courseName.toUpperCase()));
            }
        }
    }

    private void statistics() {
        Category.toList()
                .forEach(category -> System.out.printf("%s: %s\n", category.capitalize(), findCourseByCategory(category)));
    }

    /**
     * Find out which courses are the most popular ones.
     * The most popular has the biggest number of enrolled students.
     * @return List containing names of most popular courses
     */
    @Override
    public List<String> findMostPopularCourses() {
        return searchContext.setStrategy(new CourseGroupSearch(findEnrolledStudentPerCourse(studentMap))).findMax();
    }

    /**
     * Find out which courses are the least popular ones.
     * @return List containing names of the least popular courses
     */
    @Override
    public List<String> findLeastPopularCourse() {
        return searchContext.setStrategy(new CourseGroupSearch(findEnrolledStudentPerCourse(studentMap))).findMin();
    }

    /**
     * Establish the hardest course.
     * The easiest course has the lowest average grade per assignment.
     */
    @Override
    public List<String> findHardestCourse() {
        return searchContext.setStrategy(new CourseStrengthSearch(findAverageGradPerAssignmentEachCourse(assignments)))
                .findMin();
    }

    /**
     * Establish the easiest course.
     * The easiest course has the highest average grade per assignment.
     */
    @Override
    public List<String> findEasiestCourse() {
        return searchContext.setStrategy(new CourseStrengthSearch(findAverageGradPerAssignmentEachCourse(assignments)))
                .findMax();
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

    private Map<String, Double> findAverageGradPerAssignmentEachCourse(List<Assignment> assignments) {

        Map<String, Double> averageGradMap = new ConcurrentHashMap<>();
        CourseType.names().forEach(s -> {
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

    private Map<String, Long> findEnrolledStudentPerCourse(Map<Long, Student> studentMap) {
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
