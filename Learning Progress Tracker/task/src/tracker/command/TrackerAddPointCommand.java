package tracker.command;

import tracker.Tracker;
import tracker.model.Assignment;
import tracker.model.Course;
import tracker.model.Student;
import tracker.util.TrackerUtil;
import tracker.util.TrackerValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static tracker.util.TrackerUtil.BASE_COURSES;

public class TrackerAddPointCommand implements Command {

    @Override
    public void execute() {
        addPoints();
    }

    private void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");

        while (true) {
            String input = TrackerUtil.requestUserInput();

            if ("back".equals(input)) {
                return;
            }

            String[] data = input.split("\\s+");
            String studentId = data[0];

            if (!studentId.matches("\\d+")) {
                System.out.printf("No student is found for id=%s.\n", studentId);
            } else if (!TrackerValidator.valid(input)) {
                System.out.println("Incorrect points format");
            } else {
                List<Long> records = Arrays.stream(data).map(Long::parseLong).collect(Collectors.toList());
                final Long id = Long.parseLong(studentId);
                Student student = Tracker.students.get(id);
                if (student == null) {
                    System.out.printf("No student is found for id=%s.\n", studentId);
                } else {
                    update(records.subList(1, records.size()), student);
                    System.out.println("Points updated.");
                    Tracker.students.replace(id, student);
                }
            }
        }
    }

    private void update(List<Long> records, Student student) {
        IntStream.range(0, records.size())
                .forEach(i -> {
                    String name = BASE_COURSES.get(i);
                    Long points = records.get(i);
                    student.updateCourse(name, points);
                    if (points > 0) {
                        Assignment assignment = new Assignment(student.getId(), new Course(name, points));
                        Tracker.assignments.add(assignment);
                    }
                });
    }
}
