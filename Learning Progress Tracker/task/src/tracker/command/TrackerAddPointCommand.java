package tracker.command;

import tracker.domain.CourseType;
import tracker.model.Assignment;
import tracker.model.Course;
import tracker.model.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static tracker.util.TrackerValidator.POINTS_INPUT_REGEX;
import static tracker.util.TrackerValidator.matches;

public class TrackerAddPointCommand implements Command {

    public Map<Long, Student> students;
    public List<Assignment> assignments;

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

            if (validate(studentId, "\\d+")) {
                System.out.printf("No student is found for id=%s.\n", studentId);
            } else if (validate(input, POINTS_INPUT_REGEX)) {
                System.out.println("Incorrect points format");
            } else {
                final Long id = Long.parseLong(studentId);
                Student student = students.get(id);
                if (student == null) {
                    System.out.printf("No student is found for id=%s.\n", studentId);
                } else {
                    update(data.subList(1, data.size()), student);
                    System.out.println("Points updated.");
                }
            }
        }
    }

    private boolean validate(String studentId, String s) {
        return matches(s).negate().test(studentId);
    }

    private void update(List<String> records, Student student) {
        IntStream.range(0, records.size())
                .forEach(i -> {
                    String name = CourseType.get(i);
                    long points = Long.parseLong(records.get(i));
                    update(student, name, points);
                    if (points > 0) {
                        Assignment assignment = new Assignment(student.getId(), new Course(name, points));
                        assignments.add(assignment);
                    }
                });
    }

    private void update(Student student, String name, long points) {
        final Map<String, Course> courses = student.getCourses();
        if (courses.containsKey(name)) {
            Course course = courses.get(name);
            course.updatePoints(points);
        } else {
            courses.put(name, new Course(name, points));
        }
    }
}
