package tracker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tracker.domain.TrackerValidator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SampleTest {

    @Test
    void test() {
        assertEquals(5, 2 + 3);
    }

    @ParameterizedTest(name = "Should validate {0} as {1}")
    @MethodSource("pointsArgument")
    void validatePoints(String input, boolean valid) {
        assertEquals(valid, TrackerValidator.matches(TrackerValidator.POINTS_INPUT_REGEX).test(input));
    }

    static List<Arguments> pointsArgument() {
        return List.of(arguments("1000 10 10 5 8", true),
                arguments("10001 10 10 5 8", true),
                arguments("10001 5 8 7 3", true),
                arguments("10000 7 7 7 7 7", false),
                arguments("10000 ? 1 1 1", false)
        );
    }
}
