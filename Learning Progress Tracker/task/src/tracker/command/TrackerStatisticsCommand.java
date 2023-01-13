package tracker.command;

import tracker.Tracker;
import tracker.model.Assignment;
import tracker.model.Student;
import tracker.search.CourseGroupSearchContext;
import tracker.search.CourseStrengthSearchContext;
import tracker.search.Finder;

import java.util.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static tracker.util.TrackerUtil.*;

public class TrackerStatisticsCommand implements Command {

    private final Finder finder = new Finder();
    private final CourseGroupSearchContext searchContext = new CourseGroupSearchContext();
    private final CourseStrengthSearchContext strengthSearchContext = new CourseStrengthSearchContext();
    private final Map<Long, Student> studentMap = Tracker.students;
    private final List<Assignment> assignments = Tracker.assignments;

    private final Map<String, Long> courseSubmission;

    {
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
        STATISTICS_ROWS_NAMES.forEach(s -> System.out.printf("%s: %s\n", s, findCourseByCategory(s)));
        while (true) {
            String input = new Scanner(System.in).nextLine();
            if ("back".equals(input)) {
                return;
            } else if (BASE_COURSES.stream().noneMatch(s -> s.equalsIgnoreCase(input))) {
                System.out.println("Unknown course.");
            } else {
                String courseName = "dsa".equals(input) ? input.toUpperCase() : input.substring(0, 1).toUpperCase() + input.substring(1);
                showCourseDetails(courseName);
            }
        }
    }

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


    public List<String> findMostPopularCourses() {
        return finder
                .setStrategy(searchContext.setMap(findEnrolledStudentPerCourse(studentMap)))
                .findMax();
    }

    public List<String> findLeastPopularCourse() {
        return finder
                .setStrategy(searchContext.setMap(findEnrolledStudentPerCourse(studentMap)))
                .findMin();
    }

    public List<String> findHardestCourse() {
        return finder
                .setStrategy(strengthSearchContext.setMap(findAverageGradPerAssignmentEachCourse(assignments)))
                .findMin();
    }

    public List<String> findEasiestCourse() {
        return finder
                .setStrategy(strengthSearchContext.setMap(findAverageGradPerAssignmentEachCourse(assignments)))
                .findMax();
    }

    public List<String> findCourseWithHighestActivity() {
        if (courseSubmission.values().stream().allMatch((aLong -> aLong == 0))) {
            return Collections.emptyList();
        }
        return finder
                .setStrategy(searchContext.setMap(courseSubmission))
                .findMax();
    }


    public List<String> findCourseWithLowestActivity() {
        if (courseSubmission.values().stream().allMatch((aLong -> aLong == 0))) {
            return Collections.emptyList();
        }
        return finder
                .setStrategy(searchContext.setMap(courseSubmission))
                .findMin();
    }

    public void showCourseDetails(String courseName) {
        System.out.println(courseName);
        System.out.println("id\tpoints\tcompleted");
        StringBuilder stringBuilder = new StringBuilder();

        Tracker.students.values()
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
