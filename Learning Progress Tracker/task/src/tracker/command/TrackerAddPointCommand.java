package tracker.command;

import tracker.domain.CourseType;
import tracker.model.Assignment;
import tracker.model.Course;
import tracker.model.Student;
import tracker.util.TrackerValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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

            if (!studentId.matches("\\d+")) {
                System.out.printf("No student is found for id=%s.\n", studentId);
            } else if (!TrackerValidator.valid(data)) {
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

    private void update(List<String> records, Student student) {
        IntStream.range(0, records.size())
                .forEach(i -> {
                    String name = CourseType.names().get(i);
                    long points = Long.parseLong(records.get(i));
                    student.updateCourse(name, points);
                    if (points > 0) {
                        Assignment assignment = new Assignment(student.getId(), new Course(name, points));
                        assignments.add(assignment);
                    }
                });
    }
}
