package tracker.command;

import tracker.model.CourseStatus;
import tracker.model.CourseType;
import tracker.model.Assignment;
import tracker.model.Course;
import tracker.model.Student;

import java.util.*;
import java.util.stream.IntStream;

import static tracker.domain.TrackerValidator.*;

public class TrackerAddPointCommand implements Command {

    private final Map<Long, Student> students;
    private final List<Assignment> assignments;

    public TrackerAddPointCommand(Map<Long, Student> students, List<Assignment> assignments) {
        this.students = students;
        this.assignments = assignments;
    }

    @Override
    public void execute() {
        addPoints();
    }

    private void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");

        while (true) {
            String input = Command.requestUserInput();

            if (back().test(input)) {
                return;
            }

            List<String> data = Arrays.asList(input.split("\\s+"));
            String studentId = data.get(0);

            if (notMatches(studentId, "\\d+")) {
                System.out.printf("No student is found for id=%s.\n", studentId);
            } else if (notMatches(input, POINTS_INPUT_REGEX)) {
                System.out.println("Incorrect points format");
            } else {
                final Long id = Long.parseLong(studentId);
                Optional.ofNullable(students.get(id))
                        .ifPresentOrElse(student -> {
                            update(student, data.subList(1, data.size()));
                            System.out.println("Points updated.");
                        }, () -> System.out.printf("No student is found for id=%s.\n", studentId));
            }
        }
    }

    private void update(Student student, List<String> records) {
        IntStream.range(0, records.size())
                .forEach(i -> {
                    String name = CourseType.get(i);
                    long points = Long.parseLong(records.get(i));
                    update(student, name, points);
                    updateStatus(student.getCourses().get(name));
                    if (points > 0) {
                        Assignment assignment = new Assignment(student.getId(), new Course(name, points));
                        assignments.add(assignment);
                    }
                });
    }

    private void update(Student student, String courseName, long points) {
        final Map<String, Course> courses = student.getCourses();
        if (courses.containsKey(courseName)) {
            courses.get(courseName).updatePoints(points);
        } else {
            courses.put(courseName, new Course(courseName, points));
        }
    }

    private void updateStatus(Course course) {

        if (course.isCompleted()) {
            course.updateStatus(CourseStatus.COMPLETED);
        } else {
            course.updateStatus(CourseStatus.ENROLLED);
        }
    }
}
