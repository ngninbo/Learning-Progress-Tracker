package tracker.command;

import tracker.domain.CommandFactory;
import tracker.domain.StudentFactory;
import tracker.domain.TrackerAction;
import tracker.model.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class TrackerAddStudentCommand implements Command {

    public Map<Long, Student> students;

    public TrackerAddStudentCommand(Map<Long, Student> students) {
        this.students = students;
    }

    @Override
    public void execute() {
        add();
    }

    public void add() {
        System.out.println("Enter student credentials or 'back' to return:");

        while (true) {
            String input = Command.requestUserInput();
            List<String> credentials = Arrays.asList(input.split("\\s+"));
            final int size = credentials.size();

            if (credentials.contains(TrackerAction.BACK.name().toLowerCase())) {
                System.out.printf("Total %s students have been added.\n", students.size());
                return;
            } else if (size <= 2) {
                System.out.println("Incorrect credentials.");
            } else {
                String firstname = credentials.get(0);
                String email = credentials.get(size - 1).trim();

                if (exists(email)) {
                    System.out.println("This email is already taken.");
                } else {
                    StringBuilder lastname = new StringBuilder();
                    IntStream.range(1, size - 1)
                            .forEach(i -> lastname.append(credentials.get(i).concat(" ")));

                    Student student = StudentFactory.of(firstname, lastname.toString().trim(), email);

                    validate(student);
                }
            }
        }
    }

    private void validate(Student student) {
        if (!student.isValid()) {
            printValidationError(student);
        } else {
            students.put(student.getId(), student);
            System.out.println("The student has been added.");
        }
    }

    private boolean exists(String email) {
        return students.values()
                .stream()
                .anyMatch(student -> email.equals(student.getEmail()));
    }

    private void printValidationError(Student student) {
        String invalidField;

        if (!student.hasValidFirstname()) {
            invalidField = "first name";
        } else if (!student.hasValidLastname()) {
            invalidField = "last name";
        } else {
            invalidField = "email";
        }

        System.out.printf("Incorrect %s.\n", invalidField);
    }
}
