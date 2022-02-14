package tracker.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentIdGeneratorTest {

    private static StudentIdGenerator studentIdGenerator;

    @BeforeAll
    static void setUp() {
        studentIdGenerator = StudentIdGenerator.getInstance();
    }

    @Test
    @DisplayName("Should assert next id is greater or equals to 10000")
    void getNextId() {
        long next = 10000;
        assertTrue(studentIdGenerator.next() >= next);
    }
}