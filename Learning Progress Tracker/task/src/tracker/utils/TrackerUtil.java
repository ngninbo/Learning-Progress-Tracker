package tracker.utils;

import java.util.List;

public class TrackerUtil {

    public static final String ADD_STUDENTS_CMD = "add students";
    public static final String STUDENT_LIST_CMD = "list";
    public static final String ADD_POINTS_CMD = "add points";
    public static final String FIND_STUDENT_CMD = "find";
    public static final String STATISTICS_CMD = "statistics";
    public static final String NOTIFICATION_CMD = "notify";
    public static final String BACK_CMD = "back";
    public static final String EXIT_CMD = "exit";

    public static final String ENTER_EXIT_CMD = "Enter 'exit' to exit the program.";
    public static final String STUDENT_CREDENTIALS = "student credentials";
    public static final String COURSES_ACHIEVEMENT = "id and points";
    public static final String STUDENT_ID = "id";
    public static final String ENTER_CMD = "Enter %s or 'back' to return:\n";
    public static final String STATISTIC_OVERVIEW_CMD = "Type the name of a course to see details or 'back' to quit:";

    public static final String PROGRAM_TITLE = "Learning Progress Tracker";
    public static final String STUDENT_DETAIL_TEXT = "%s points: ";
    public static final String PROGRESS_DATA_UPDATE_SUCCEED_MSG = "Points updated.";
    public static final String TOTAL_STUDENT_REPORT = "Total %d students have been added.\n";
    public static final String STUDENT_ADD_SUCCEED_MSG = "The student has been added.";
    public static final String STUDENT_LIST_OVERVIEW_HEADER = "Students:";
    public static final String OUTPUT_MSG_ON_EXIT = "Bye!";

    public static final String STUDENT_WITH_ID_NOT_FOUND_MSG = "No student is found for id=%s.\n";
    public static final String STUDENT_LIST_EMPTY_MSG = "No students found";
    public static final String ERROR_UNKNOWN_COMMAND = "Error: unknown command!";
    public static final String NO_INPUT = "No input.";
    public static final String FIRSTNAME = "first name";
    public static final String LASTNAME = "last name";
    public static final String EMAIL = "email";
    public static final String CREDENTIALS = "credentials";
    public static final String POINTS_FORMAT = "points format";
    public static final String ERROR_MSG_FORMAT = "Incorrect %s.\n";
    public static final String NOT_AVAILABLE_EMAIL = "This email is already taken.";

    public static final String NAME_REGEX = "^[A-Za-z]+[-']?[A-Za-z]+";
    public static final String LASTNAME_REGEX = "[A-Za-z ]+([-' ][A-Za-z ])*[-' ]?[A-Za-z]+";
    public static final String EMAIL_REGEX = "[\\w.]+@\\w+\\.[a-z\\d]+";
    public static final String POINT_FORMAT_REGEX = "[\\d{1,9}]+";
    public static final String NOTIFICATION_MSG_FORMAT = "To: %s\n" +
            "Re: Your Learning Progress\n" +
            "Hello, %s! You have accomplished our %s course!\n";
    public static final String NOTIFICATION_SUCCEED_MSG = "Total %s students have been notified.\n";

    public static final String JAVA = "Java";
    public static final String DSA = "DSA";
    public static final String DATABASES = "Databases";
    public static final String SPRING = "Spring";

    public static final List<String> BASE_COURSES = List.of(
            JAVA,
            DSA,
            DATABASES,
            SPRING
    );

    public static final List<String> STATISTICS_ROWS_NAMES = List.of(
            "Most popular",
            "Least popular",
            "Highest activity",
            "Lowest activity",
            "Easiest course",
            "Hardest course"
    );

    public static final long MIN_ID = 9999L;
    public static final long MAX_POINTS_JAVA = 600;
    public static final long MAX_POINTS_DSA = 400;
    public static final long MAX_POINTS_DATABASES = 480;
    public static final long MAX_POINTS_SPRING = 550;

    public static final int PROGRESS_DATA_SIZE = 5;
    public static final String UNKNOWN_COURSE_MSG = "Unknown course.";
    public static final double PERCENT = 100.0;
}
