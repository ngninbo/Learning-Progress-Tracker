package tracker.model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tracker.builder.StudentBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StudentTest {

    private Student student;

    @ParameterizedTest (name = "Should return {3} for firstname = {0}, lastname = {1} and email = {2}")
    @MethodSource("argCredentialsFactory")
    void testValidityCredentials(String firstname, String lastname, String email, boolean valid) {
       student = StudentBuilder.init()
               .withFirstname(firstname)
               .withLastname(lastname)
               .withEmail(email)
               .build();
        assertEquals(valid, student.isValid());
    }


    @ParameterizedTest (name = "Should throw NullPointException for firstname = {0}, lastname = {1} and email = {2}")
    @MethodSource("argExceptionFactory")
    void testException(String firstname, String lastname, String email) {

        StudentBuilder studentBuilder = StudentBuilder.init();

        if (firstname != null) {
            studentBuilder = studentBuilder.withFirstname(firstname);
        }

        if (lastname != null) {
            studentBuilder = studentBuilder.withLastname(lastname);
        }

        if (email != null) {
            studentBuilder = studentBuilder.withEmail(email);
        }

        student = studentBuilder.build();

        assertThrows(NullPointerException.class, student::isValid);
    }

    static List<Arguments> argCredentialsFactory() {
        return List.of(
                arguments("Jean-Clause", "van Helsing", "jc@google.it", true),
                arguments("Mary", "Luise Johnson", "maryj@google.com", true),
                arguments("John", "Doe", "johnd@yahoo.com", true),
                arguments("John", "Doe", "jdoe@yahoo.com", true),
                arguments("Jane", "Spark", "jspark@gmail.com", true),
                arguments("Jane", "Spark", "janes@gmail.com", true),
                arguments("John", "Doe", "email", false),
                arguments("John", "D.", "name@domain.com", false),
                arguments("陳", "港", "生", false),
                arguments("J.", "Doe", "name@domain.com", false));
    }

    static List<Arguments> argExceptionFactory() {
        return List.of(
                arguments("Jean-Clause", null, "jc@google.it"),
                arguments(null, "Luise Johnson", "maryj@google.com"),
                arguments("John", "Doe", null)
        );
    }

    @Nested
    class NestedTest {

        private static Student student;

        @BeforeAll
        static void setUp() {
            student = new Student("John", "Doe", "email");
        }

        @Test
        @DisplayName("Should return true when student has valid first name")
        void hasValidFirstName() {
            assertTrue(student.hasValidFirstName());
        }

        @Test
        @DisplayName("Should return true when student has valid last name")
        void hasValidLastName() {
            assertTrue(student.hasValidLastName());
        }

        @Test
        @DisplayName("Should return false when student has invalid email")
        void hasValidEmail() {
            assertFalse(student.hasValidEmail());
        }

        @Test
        @DisplayName("Should return false when any credential is not valid")
        void isValid() {
            assertFalse(student.isValid());
        }
    }
}