package tracker.command;

import tracker.domain.StudentFactory;
import tracker.domain.TrackerAction;
import tracker.model.Student;
import tracker.domain.TrackerValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class TrackerAddStudentCommand implements Command {

    private final Map<Long, Student> students;

    public TrackerAddStudentCommand(Map<Long, Student> students) {
        this.students = students;
    }

    @Override
    public void execute() {
        add();
    }

    private void add() {
        System.out.println("Enter student credentials or 'back' to return:");

        while (true) {
            String input = Command.requestUserInput();
            List<String> credentials = Arrays.asList(input.split("\\s+"));
            final int size = credentials.size();

            if (input.contains(TrackerAction.BACK.name().toLowerCase())) {
                System.out.printf("Total %s students have been added.\n", students.size());
                return;
            } else if (size <= 2) {
                System.out.println("Incorrect credentials.");
            } else {
                addStudent(credentials);
            }
        }
    }

    public void addStudent(List<String> credentials) {
        final int size = credentials.size();
        String firstname = credentials.get(0);
        String email = credentials.get(size - 1).trim();

        if (exists(email)) {
            System.out.println("This email is already taken.");
        } else {
            StringBuilder lastname = new StringBuilder();
            IntStream.range(1, size - 1)
                    .forEach(i -> lastname.append(credentials.get(i).concat(" ")));

            Student student = StudentFactory.of(firstname, lastname.toString().trim(), email);

            if (TrackerValidator.valid(student)) {
                students.put(student.getId(), student);
                System.out.println("The student has been added.");
            }
        }
    }

    private boolean exists(String email) {
        return students.values()
                .stream()
                .anyMatch(student -> email.equals(student.getEmail()));
    }
}
