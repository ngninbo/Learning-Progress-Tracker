package tracker.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest(name = "Check if course is enrolled. Should end with {1}")
    @CsvSource({"1000, true", "10, true", "10, true", "5, true", "8, true", "-1, false", "0, false"})
    void hasValidPoints(Long points, boolean valid) {
        Course course = new Course("Java", points);
        assertEquals(valid, course.isEnrolled());
    }
}