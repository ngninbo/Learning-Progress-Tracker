package tracker.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ValidatorTest {

    @Test
    void isValidName() {
    }

    @Test
    void isValidLastname() {
    }

    @Test
    void isValidEmail() {
    }

    @Test
    void isValidPointFormat() {
    }

    @ParameterizedTest(name = "Should return {1} for list {0}")
    @MethodSource("argFactory")
    void isValid(List<Long> data, boolean valid) {
        assertEquals(valid, Validator.isValid(data));
    }

    @ParameterizedTest(name = "Should throw exception for invalid list {0}")
    @MethodSource("argFactoryInvalidFormat")
    void isNotValid(List<Long> data) {
        assertThrows(ClassCastException.class, () -> Validator.isValid(data));
    }

    static List<Arguments> argFactory() {
        return List.of(
                arguments(List.of(10001L, 10L, 10L, 5L, 8L), true),
                arguments(List.of(10001L, 5L, 8L, 7L, 3L), true),
                arguments(List.of(10000L, -1L, 2L, 2L, 2L), false),
                arguments(List.of(10000L, 7L, 7L, 7L, 7L, 7L), false));
    }

    static List<Arguments> argFactoryInvalidFormat() {
        return List.of(
                arguments(List.of(10000L, "?", 1L, 1L, 1L)));
    }
}