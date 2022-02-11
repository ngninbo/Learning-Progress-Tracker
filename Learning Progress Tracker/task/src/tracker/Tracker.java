package tracker;

import tracker.model.Assignment;
import tracker.model.Course;
import tracker.model.Student;
import tracker.builder.StudentBuilder;
import tracker.notification.NotificationService;
import tracker.statistics.Statistic;
import tracker.utils.StudentIdGenerator;
import tracker.utils.Validator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static tracker.utils.TrackerHelperFunction.*;

public class Tracker {

    private Map<Long, Student> studentMap;
    private final StudentIdGenerator idGenerator;
    private final Map<String, Long> submissions;
    private final List<Assignment> assignments;
    private final Statistic statistic;
    private final NotificationService notificationService;

    {
        idGenerator = StudentIdGenerator.getInstance();
    }

    public Tracker(Map<Long, Student> studentMap,
                   Map<String, Long> submissions,
                   List<Assignment> assignments,
                   Statistic statistic, NotificationService notificationService) {
        this.studentMap = studentMap;
        this.submissions = submissions;
        this.assignments = assignments;
        this.statistic = statistic;
        this.notificationService = notificationService;
    }

    public void start() {
        System.out.println(PROGRAM_TITLE);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(NO_INPUT);
            } else {
                switch (input) {
                    case EXIT_CMD:
                        System.out.println(OUTPUT_MSG_ON_EXIT);
                        exit = true;
                        break;
                    case STUDENT_LIST_CMD:
                        showOverview();
                        break;
                    case ADD_STUDENTS_CMD:
                        addStudents();
                        break;
                    case ADD_POINTS_CMD:
                        addPoints();
                        break;
                    case FIND_STUDENT_CMD:
                        findStudent();
                        break;
                    case STATISTICS_CMD:
                        showStatistics();
                        break;
                    case NOTIFICATION_CMD:
                        sendNotificationMessage();
                        break;
                    case BACK_CMD:
                        System.out.println(ENTER_EXIT_CMD);
                        break;
                    default:
                        System.out.println(ERROR_UNKNOWN_COMMAND);

                }
            }
        }
    }

    private void sendNotificationMessage() {
        studentMap = notificationService.setStudents(studentMap).sendNotificationMessage();
    }

    private void showStatistics() {
        statistic.setStudentMap(studentMap)
                .setCourseSubmission(submissions)
                .setAssignments(assignments);
        boolean showStat = true;
        System.out.println(STATISTIC_OVERVIEW_CMD);
        STATISTICS_ROWS_NAMES.forEach(s -> System.out.printf("%s: %s\n", s, statistic.findCourseByCategory(s)));
        while (showStat) {
            String input = new Scanner(System.in).nextLine();
            if (BACK_CMD.equals(input)) {
                showStat = false;
            } else if (BASE_COURSES.stream().noneMatch(s -> s.equalsIgnoreCase(input))) {
                System.out.println(UNKNOWN_COURSE_MSG);
            } else {
                String courseName = "dsa".equals(input) ? input.toUpperCase() : input.substring(0, 1).toUpperCase() + input.substring(1);
                statistic.showCourseDetails(courseName);
            }
        }
    }

    private void findStudent() {
        System.out.println(ENTER_STUDENT_ID_CMD);
        boolean goBack = false;
        while (!goBack) {

            String userInput = new Scanner(System.in).nextLine();

            if (!userInput.matches(POINT_FORMAT_REGEX)) {
                goBack = true;
            } else {
                long id = Long.parseLong(userInput);
                if (isStudentNotFound(id)) {
                    System.out.printf(STUDENT_WITH_ID_NOT_FOUND_MSG, id);
                } else {
                    System.out.println(stringifyProgressData(studentMap.get(id)));
                }
            }

        }
    }

    private void addPoints() {
        boolean isBackEntered = false;
        System.out.println(ENTER_STUDENT_COURSES_INFORMATION_CMD);
        while (!isBackEntered) {
            List<String> progressData = List.of(new Scanner(System.in).nextLine().split("\\s"));
            final String studentId = progressData.get(0);

            if (BACK_CMD.equals(studentId)) {
                isBackEntered = true;
            } else if (!Validator.isValidPointFormat(studentId)) {
                    System.out.printf(STUDENT_WITH_ID_NOT_FOUND_MSG, studentId);
            } else {
                List<Long> data = progressData.stream()
                        .filter(Validator::isValidPointFormat)
                        .map(Long::valueOf)
                        .collect(Collectors.toList());
                boolean succeed = updateStudentCoursePoints(data);
                if (succeed) {
                    System.out.println(PROGRESS_DATA_UPDATE_SUCCEED_MSG);
                }
            }
        }
    }

    public boolean updateStudentCoursePoints(List<Long> progressData) {

        if (!Validator.isValid(progressData)) {
            System.out.println(INCORRECT_POINTS_FORMAT);
        } else {
            long id = progressData.get(0);
            if (isStudentNotFound(id)) {
                System.out.printf(STUDENT_WITH_ID_NOT_FOUND_MSG, id);
            } else {
                Student student = studentMap.get(id);
                IntStream.range(1, PROGRESS_DATA_SIZE)
                        .forEach(i -> {
                            String name = BASE_COURSES.get(i - 1);
                            Long points = progressData.get(i);
                            student.updateCourse(name, points);
                            if (points > 0) {
                                long count = submissions.get(name) + 1;
                                submissions.put(name, count);
                                Assignment assignment = new Assignment(id, new Course(name, points));
                                assignments.add(assignment);
                            }
                        });

                studentMap.replace(id, student);
                return true;
            }
        }

        return false;
    }

    public boolean isStudentNotFound(long id) {
        return studentMap.keySet().stream().noneMatch(studentId -> studentId == id);
    }

    private void showOverview() {
        if (studentMap.isEmpty()) {
            System.out.println(STUDENT_LIST_EMPTY_MSG);
        } else {
            System.out.println(STUDENT_LIST_OVERVIEW_HEADER);
            studentMap.keySet().forEach(System.out::println);
        }
    }

    private void addStudents() {

        System.out.println(ENTER_CREDENTIALS_CMD);
        boolean goBack = false;

        while (!goBack) {
            List<String> credentials = List.of(new Scanner(System.in).nextLine().split("\\s"));
            int size = credentials.size();

            if (credentials.contains(BACK_CMD)) {
                System.out.printf(TOTAL_STUDENT_REPORT, studentMap.size());
                goBack = true;
            } else if (size <= 2) {
                System.out.println(INCORRECT_CREDENTIALS);
            } else {

                String firstname = credentials.get(0).trim();

                StringBuilder lastname = new StringBuilder();

                IntStream.range(1, size - 1)
                        .forEach(i -> lastname.append(credentials.get(i)).append(" "));

                String email = credentials.get(size - 1).trim();

                long studentId = idGenerator.getNextId();

                Student student = StudentBuilder.init()
                        .withId(studentId)
                        .withCredentials(firstname, lastname.toString().trim(), email)
                        .withCourses()
                        .build();

                if (validateCredentials(student)) {
                    System.out.println(STUDENT_ADD_SUCCEED_MSG);
                }
            }
        }
    }

    public boolean validateCredentials(Student student) {

        if (student.isValid()) {
            if (isEmailTaken(student.getEmail())) {
                System.out.println(NOT_AVAILABLE_EMAIL);
            } else {
                studentMap.put(student.getId(), student);
                return true;
            }

        } else {
            if (!student.hasValidFirstName()) {
                System.out.println(INCORRECT_FIRSTNAME);
            } else if (!student.hasValidLastName()) {
                System.out.println(INCORRECT_LASTNAME);
            } else {
                System.out.println(INCORRECT_EMAIL);
            }
        }

        return false;
    }

    public boolean isEmailTaken(String email) {
        return studentMap.entrySet()
                .stream()
                .anyMatch(studentEntry -> email.equals(studentEntry.getValue().getEmail()));
    }
}
