package tracker.service.statistics;

import tracker.model.Assignment;
import tracker.model.Student;
import tracker.service.search.CourseGroupSearchContext;
import tracker.service.search.CourseStrengthSearchContext;
import tracker.service.search.Finder;

import java.util.*;
import static tracker.utils.TrackerHelperFunction.*;

public class TrackerStatistics implements Statistic {

    private Map<Long, Student> studentMap;
    private Map<String, Long> courseSubmission;
    private List<Assignment> assignments;

    private final Finder finder;
    private final CourseGroupSearchContext searchContext;
    private final CourseStrengthSearchContext strengthSearchContext;

    public TrackerStatistics(Map<Long, Student> studentMap,
                             Map<String, Long> courseSubmission,
                             List<Assignment> assignments,
                             Finder finder,
                             CourseGroupSearchContext searchContext,
                             CourseStrengthSearchContext courseStrengthSearchContext) {
        this.studentMap = studentMap;
        this.courseSubmission = courseSubmission;
        this.assignments = assignments;
        this.finder = finder;
        this.searchContext = searchContext;
        this.strengthSearchContext = courseStrengthSearchContext;
    }

    public Statistic setStudentMap(Map<Long, Student> studentMap) {
        this.studentMap = studentMap;
        return this;
    }

    public Statistic setCourseSubmission(Map<String, Long> courseSubmission) {
        this.courseSubmission = courseSubmission;
        return this;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public String findCourseByCategory(String category) {

        String stringBuilder;
        List<String> courses;

        switch (category) {
            case "Most popular":
                stringBuilder = stringifyCourseList(findMostPopularCourses());
                break;
            case "Least popular":
                courses = findLeastPopularCourse();
                courses.removeAll(findMostPopularCourses());
                stringBuilder = stringifyCourseList(courses);
                break;
            case "Highest activity":
                stringBuilder = stringifyCourseList(findCourseWithHighestActivity());
                break;
            case "Lowest activity":
                courses = findCourseWithLowestActivity();
                courses.removeAll(findCourseWithHighestActivity());
                stringBuilder = stringifyCourseList(courses);
                break;
            case "Easiest course":
                stringBuilder = stringifyCourseList(findEasiestCourse());
                break;
            case "Hardest course":
                courses = findHardestCourse();
                courses.removeAll(findEasiestCourse());
                stringBuilder = stringifyCourseList(courses);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }

        return stringBuilder.isEmpty() ? "n/a" : stringBuilder;
    }


    @Override
    public List<String> findMostPopularCourses() {
        return finder.setStrategy(searchContext.setMap(findEnrolledStudentPerCourse(studentMap)))
                .findMax();
    }

    @Override
    public List<String> findLeastPopularCourse() {
        return finder.setStrategy(searchContext.setMap(findEnrolledStudentPerCourse(studentMap)))
                .findMin();
    }

    @Override
    public List<String> findHardestCourse() {
        return finder.setStrategy(strengthSearchContext
                .setMap(findAverageGradPerAssignmentEachCourse(assignments)))
                .findMin();
    }

    @Override
    public List<String> findEasiestCourse() {
        return finder.setStrategy(strengthSearchContext
                .setMap(findAverageGradPerAssignmentEachCourse(assignments)))
                .findMax();
    }

    @Override
    public List<String> findCourseWithHighestActivity() {
        if (courseSubmission.values().stream().allMatch((aLong -> aLong == 0))) {
            return new ArrayList<>();
        }
        return finder.setStrategy(searchContext
                .setMap(courseSubmission))
                .findMax();
    }

    @Override
    public List<String> findCourseWithLowestActivity() {
        if (courseSubmission.values().stream().allMatch((aLong -> aLong == 0))) {
            return new ArrayList<>();
        }
        return finder.setStrategy(searchContext
                .setMap(courseSubmission))
                .findMin();
    }

    @Override
    public void showCourseDetails(String courseName) {
        System.out.println(courseName);
        System.out.println("id\tpoints\tcompleted");
        StringBuilder stringBuilder = new StringBuilder();

        studentMap.values()
                .stream()
                .filter(student -> student.isEnrolled(courseName))
                .sorted(Comparator.comparing(student -> student.sumPoints(courseName), Comparator.reverseOrder()))
                .forEachOrdered(student -> stringBuilder.append(student.getId()).append("\t")
                        .append(student.sumPoints(courseName)).append("\t")
                        .append(student.progress(courseName)).append("%\n"));

        if (!stringBuilder.toString().isEmpty()) {
            System.out.print(stringBuilder);
        }
    }
}
