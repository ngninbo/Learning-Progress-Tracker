package tracker;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tracker.builder.TrackerBuilder;
import tracker.model.Student;
import tracker.builder.StudentBuilder;
import tracker.utils.StudentIdGenerator;
import tracker.utils.TrackerHelperFunction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static tracker.utils.TrackerUtil.MIN_ID;

class TrackerTest {

    public static StudentIdGenerator studentIdGenerator;
    public static Tracker tracker;

    @BeforeAll
    static void beforeAll() {
        studentIdGenerator = StudentIdGenerator.getInstance();
        tracker = TrackerBuilder.init()
                .withTrackerController()
                .build();
    }

    @ParameterizedTest(name = "Should add student with firstname = {0}, lastname = {2} and email = {3}")
    @MethodSource("argStudentFactory")
    void testAddStudent(String firstname, String lastname, String email) {

        final Long nextId = studentIdGenerator.next();
        assertTrue(nextId > MIN_ID);
        Student student = StudentBuilder.init()
                .withId(nextId)
                .withCredentials(firstname, lastname, email)
                .withCourses()
                .build();

        assertEquals(nextId, student.getId());

        assertTrue(tracker.getTrackerService().validateCredentials(student));
    }

    @ParameterizedTest(name = "Update progress data for {0}. Should return with {1}")
    @MethodSource("argCoursePointsFactory")
    void updateStudentCoursePoints(List<Long> progressData, boolean isUpdate) {
        assertEquals(isUpdate, tracker.getTrackerService().updateStudentCoursePoints(progressData));
    }


    @ParameterizedTest(name = "Should throw exception for invalid list {0}")
    @MethodSource("argFactoryInvalidFormat")
    void isNotValid(List<Long> data) {
        assertThrows(ClassCastException.class, () -> tracker.getTrackerService().updateStudentCoursePoints(data));
    }

    static List<Arguments> argStudentFactory() {
        return List.of(
                arguments("Jean-Clause", "van Helsing", "jc@google.it"),
                arguments("Mary", "Luise Johnson", "maryj@google.com"),
                arguments("John", "Doe", "johnd@yahoo.com"),
                arguments("John", "Doe", "jdoe@yahoo.com"),
                arguments("Jane", "Spark", "jspark@gmail.com"),
                arguments("Jane", "Spark", "janes@gmail.com"));
    }

    static List<Arguments> argCoursePointsFactory() {
        return List.of(
                arguments(List.of(1000L, 10L, 10L, 5L, 8L), false),
                arguments(List.of(10001L, 10L, 10L, 5L, 8L), true),
                arguments(List.of(10001L, 5L, 8L, 7L, 3L), true),
                arguments(List.of(10000L, -1L, 2L, 2L, 2L), false),
                arguments(List.of(10000L, 7L, 7L, 7L, 7L, 7L), false),
                arguments(List.of(10000L, 5L, 5L, 5L, 5L), true),
                arguments(List.of(10000L, 7L, 8L, 9L, 10L), true));
    }

    static List<Arguments> argFactoryInvalidFormat() {
        return List.of(
                arguments(List.of(10000L, "?", 1L, 1L, 1L)));
    }

    @Nested
    class NestedTest {

        @Test
        @DisplayName("Should return true when student by id = 1000 not found")
        void isStudentNotFound() {
            long invalidId = 1000;
            assertTrue(tracker.getTrackerService().isStudentNotMatch(invalidId));
        }

        @Test
        @DisplayName("Should return false when student by id = 10000 found")
        void isStudentFound() {
            long invalidId = 10000;
            assertFalse(tracker.getTrackerService().isStudentNotMatch(invalidId));
        }

        @ParameterizedTest (name = "Check that the progress data of student with id = {0} were updated correctly")
        @MethodSource("argCoursePointsUpdateFactory")
        void testCoursePointUpdateCorrectly(long studentId, String expected) {
            Student student = tracker.getStudentMap().get(studentId);
            assertEquals(expected, TrackerHelperFunction.stringifyProgressData(student));
        }

        static List<Arguments> argCoursePointsUpdateFactory() {
            return List.of(
                    arguments(10001L, "10001 points: Java=15; DSA=18; Databases=12; Spring=11"),
                    arguments(10000L, "10000 points: Java=12; DSA=13; Databases=14; Spring=15"));
        }
    }
}