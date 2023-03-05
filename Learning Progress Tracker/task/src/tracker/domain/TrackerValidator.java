package tracker.domain;

import tracker.model.Student;

import java.util.function.Predicate;

public class TrackerValidator {

    public static final String NAME_REGEX = "^[A-Za-z]+[-']?[A-Za-z]+";
    public static final String LASTNAME_REGEX = "[A-Za-z ]+([-' ][A-Za-z ])*[-' ]?[A-Za-z]+";
    public static final String EMAIL_REGEX = "[\\w.]+@\\w+\\.[a-z\\d]+";
    public static final String POINTS_INPUT_REGEX = "(\\d+\\s){4}\\d+";

    public static Predicate<String> matches(String regex) {
        return s -> s.matches(regex);
    }

    public static boolean valid(Student student) {
        String invalidField;

        if (notMatches(student.getFirstname(), NAME_REGEX)) {
            invalidField = "first name";
        } else if (notMatches(student.getLastname(), LASTNAME_REGEX)) {
            invalidField = "last name";
        } else if (notMatches(student.getEmail(), EMAIL_REGEX)){
            invalidField = "email";
        } else {
            return true;
        }

        System.out.printf("Incorrect %s.\n", invalidField);
        return false;
    }

    public static boolean notMatches(String field, String regex) {
        return matches(regex).negate().test(field);
    }
}
