package tracker.util;

import java.util.List;

public class TrackerValidator {

    public static final String NAME_REGEX = "^[A-Za-z]+[-']?[A-Za-z]+";
    public static final String LASTNAME_REGEX = "[A-Za-z ]+([-' ][A-Za-z ])*[-' ]?[A-Za-z]+";
    public static final String EMAIL_REGEX = "[\\w.]+@\\w+\\.[a-z\\d]+";

    public static boolean validate(String firstname) {
        return firstname.matches(NAME_REGEX);
    }

    public static boolean isValidLastname(String lastname) {
        return lastname.matches(LASTNAME_REGEX);
    }

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean valid(List<String> data) {
        return data.size() == 5 && allNumeric(data);
    }

    private static boolean allNumeric(List<String> data) {
        return data.stream().allMatch(s -> s.matches("\\d+"));
    }
}
