package tracker.command;

import tracker.Tracker;
import tracker.domain.NumberGenerator;
import tracker.model.Student;
import tracker.util.TrackerUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class TrackerAddStudentCommand implements Command {

    @Override
    public void execute() {
        add();
    }

    public void add() {
        System.out.println("Enter student credentials or 'back' to return:");
        String input;
        while (true) {
            input = TrackerUtil.requestUserInput();
            List<String> credentials = Arrays.asList(input.split("\\s+"));
            final int size = credentials.size();

            if (credentials.contains("back")) {
                System.out.printf("Total %s students have been added.\n", Tracker.students.size());
                return;
            } else if (size <= 2) {
                System.out.println("Incorrect credentials.");
            } else {
                String firstname = credentials.get(0);
                String email = credentials.get(size - 1).trim();

                if (exist(email)) {
                    System.out.println("This email is already taken.");
                } else {
                    StringBuilder lastname = new StringBuilder();
                    IntStream.range(1, size - 1)
                            .forEach(i -> lastname.append(credentials.get(i)).append(" "));

                    Student student = Student.builder()
                            .id(NumberGenerator.getInstance().next())
                            .firstname(firstname)
                            .lastname(lastname.toString().trim())
                            .email(email)
                            .courses(new HashMap<>())
                            .build();

                    validate(student);
                }
            }
        }
    }

    private void validate(Student student) {
        if (!student.isValid()) {
            if (!student.hasValidFirstname()) {
                TrackerUtil.logInfoForIncorrectValue("first name");
            } else if (!student.hasValidLastname()) {
                TrackerUtil.logInfoForIncorrectValue("last name");
            } else {
                TrackerUtil.logInfoForIncorrectValue("email");
            }
        } else {
            Tracker.students.put(student.getId(), student);
            System.out.println("The student has been added.");
        }
    }

    private boolean exist(String email) {
        return Tracker.students.values()
                .stream()
                .anyMatch(student -> email.equals(student.getEmail()));
    }
}
