package tracker.statistics;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tracker.utils.TrackerHelperFunction;
import tracker.utils.TrackerTestUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackerAnalyserAnotherTest {

    private static Statistic statistic;

    @BeforeAll
    static void setUp() {
        List<List<Long>> testData = List.of(
                List.of(10000L, 8L, 7L, 7L, 5L),
                List.of(10000L, 7L, 6L, 9L, 7L),
                List.of(10000L, 6L, 5L, 5L, 0L),
                List.of(10001L, 8L, 0L, 8L, 6L),
                List.of(10001L, 9L, 0L, 0L, 5L),
                List.of(10001L, 7L, 0L, 0L, 0L));

        statistic = TrackerTestUtil.init(testData);
    }

    @Test
    @DisplayName("Should return true when the most popular courses are Java, Databases, Spring")
    void findMostPopularCourses() {
        final List<String> mostPopularCourses = statistic.findMostPopularCourses();
        assertEquals(List.of("Java", "Databases", "Spring"), mostPopularCourses);
    }

    @Test
    @DisplayName("Should return true when the least popular course is DSA")
    void findLeastPopularCourse() {
        assertEquals(List.of("DSA"), statistic.findLeastPopularCourse());
    }

    @Test
    @DisplayName("Should return true when Java is the course with highest activity")
    void findCourseWithHighestActivity() {
        assertEquals(List.of("Java"), statistic.findCourseWithHighestActivity());
    }

    @Test
    @DisplayName("Should return true when DSA is the course with highest activity")
    void findCourseWithLowestActivity() {
        assertEquals(List.of("DSA"), statistic.findCourseWithLowestActivity());
    }

    @Test
    @DisplayName("Should assert the hardest course to be Spring")
    void findHardestCourse() {
        assertEquals(List.of("Spring"), statistic.findHardestCourse());
    }

    @Test
    @DisplayName("Should assert the easiest course to be Java")
    void findEasiestCourse() {
        assertEquals(List.of("Java"), statistic.findEasiestCourse());
    }
}