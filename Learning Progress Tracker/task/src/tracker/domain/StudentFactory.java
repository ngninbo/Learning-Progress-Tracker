package tracker.domain;

import tracker.model.Student;

import java.util.HashMap;

public class StudentFactory {

    public static Student of(String firstname, String lastname, String email) {
        return Student.builder()
                .id(NumberGenerator.getInstance().next())
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .courses(new HashMap<>())
                .build();
    }
}
