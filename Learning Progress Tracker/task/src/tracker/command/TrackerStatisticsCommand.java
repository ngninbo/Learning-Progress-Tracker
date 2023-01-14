package tracker.command;

import tracker.Tracker;
import tracker.model.Assignment;
import tracker.model.Student;
import tracker.search.CourseGroupSearchContext;
import tracker.search.CourseStrengthSearchContext;
import tracker.search.Finder;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
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
            String courseName = new Scanner(System.in).nextLine();
            if (BACK_COMMAND.equals(courseName)) {
                return;
            } else if (BASE_COURSES.stream().noneMatch(s -> s.equalsIgnoreCase(courseName))) {
                System.out.println("Unknown course.");
            } else {
                showTopLearners(courseName);
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


    /**
     * Find out which courses are the most popular ones.
     * The most popular has the biggest number of enrolled students.
     * @return List containing names of most popular courses
     */
    public List<String> findMostPopularCourses() {
        return finder
                .setStrategy(searchContext.setMap(findEnrolledStudentPerCourse(studentMap)))
                .findMax();
    }

    /**
     * Find out which courses are the least popular ones.
     * @return List containing names of the least popular courses
     */
    public List<String> findLeastPopularCourse() {
        return finder
                .setStrategy(searchContext.setMap(findEnrolledStudentPerCourse(studentMap)))
                .findMin();
    }

    /**
     * Establish the hardest course.
     * The easiest course has the lowest average grade per assignment.
     */
    public List<String> findHardestCourse() {
        return finder
                .setStrategy(strengthSearchContext.setMap(findAverageGradPerAssignmentEachCourse(assignments)))
                .findMin();
    }

    /**
     * Establish the easiest course.
     * The easiest course has the highest average grade per assignment.
     */
    public List<String> findEasiestCourse() {
        return finder
                .setStrategy(strengthSearchContext.setMap(findAverageGradPerAssignmentEachCourse(assignments)))
                .findMax();
    }

    /**
     * Find out which course has the highest student activity.
     * Higher student activity means a bigger number of completed tasks;
     * @return List containing names of course with the highest activity
     */
    public List<String> findCourseWithHighestActivity() {
        if (courseSubmission.values().stream().allMatch((aLong -> aLong == 0))) {
            return Collections.emptyList();
        }
        return finder
                .setStrategy(searchContext.setMap(courseSubmission))
                .findMax();
    }


    /**
     * Find out which course has the lowest student activity.
     * @return List containing names of course with the lowest activity
     */
    public List<String> findCourseWithLowestActivity() {
        if (courseSubmission.values().stream().allMatch((aLong -> aLong == 0))) {
            return Collections.emptyList();
        }
        return finder
                .setStrategy(searchContext.setMap(courseSubmission))
                .findMin();
    }

    /**
     * Establish top learners for given course.
     * @param courseName Courses name
     */
    public void showTopLearners(String courseName) {
        String name = "dsa".equals(courseName) ? courseName.toUpperCase() : capitalize(courseName);
        System.out.printf("%s\nid\tpoints\tcompleted\n", name);

        Tracker.students.values()
                .stream()
                .filter(student -> student.isEnrolled(name))
                .sorted(comparing(student -> student.sumPoints(name), reverseOrder()))
                .map(student -> student.completion(name))
                .filter(s -> !s.isEmpty())
                .forEachOrdered(System.out::print);
    }
}
