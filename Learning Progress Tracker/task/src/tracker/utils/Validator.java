package tracker.utils;

import java.util.List;

import static tracker.utils.TrackerUtil.*;

public class Validator {

    public static boolean isValidName(String name) {
        return name.matches(NAME_REGEX);
    }

    public static boolean isValidLastname(String lastname) {
        return lastname.matches(LASTNAME_REGEX);
    }

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValidPointFormat(String point) {
        return point.matches(POINT_FORMAT_REGEX);
    }

    public static boolean isValid(List<Long> progressData) {
        return progressData.size() == PROGRESS_DATA_SIZE && progressData.stream().allMatch(number -> number >= 0L);
    }
}
