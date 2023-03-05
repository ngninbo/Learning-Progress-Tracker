package tracker.command;

import tracker.domain.CourseType;
import tracker.model.Student;

import java.util.Map;

public class TrackerFindCommand implements Command {

    public Map<Long, Student> students;

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
                Student student = students.get(Long.parseLong(input));
                if (student == null) {
                    System.out.printf("No student is found for id=%s\n", input);
                } else {
                    printCoursePoints(student);
                }
            }
        }
    }

    public void printCoursePoints(Student student) {
        StringBuilder sb = new StringBuilder(String.format("%s points: ", student.getId()));
        student.getCourses().forEach((s, course) -> {
            String format = CourseType.SPRING.name().equalsIgnoreCase(s) ? "%s=%d" : "%s=%d; ";
            sb.append(String.format(format, s, course.getPoints()));
        });

        System.out.println(sb);
    }
}
