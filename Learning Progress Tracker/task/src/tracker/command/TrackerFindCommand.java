package tracker.command;

import tracker.model.CourseType;
import tracker.model.Student;

import java.util.Map;
import java.util.Optional;

public class TrackerFindCommand implements Command {

    private final Map<Long, Student> students;

    public TrackerFindCommand(Map<Long, Student> students) {
        this.students = students;
    }

    @Override
    public void execute() {
        find();
    }

    private void find() {
        System.out.println("Enter an id or 'back' to return");
        while (true) {
            String input = Command.requestUserInput();

            if (back().test(input)) {
                return;
            }

            if (input.matches("\\d+")) {
                Optional.ofNullable(students.get(Long.parseLong(input)))
                        .ifPresentOrElse(this::printCoursePoints,
                                () -> System.out.printf("No student is found for id=%s\n", input));
            }
        }
    }

    private void printCoursePoints(Student student) {
        StringBuilder sb = new StringBuilder(String.format("%s points: ", student.getId()));
        student.getCourses().forEach((s, course) -> {
            String format = CourseType.SPRING.name().equalsIgnoreCase(s) ? "%s=%d" : "%s=%d; ";
            sb.append(String.format(format, s, course.getPoints()));
        });

        System.out.println(sb);
    }
}
