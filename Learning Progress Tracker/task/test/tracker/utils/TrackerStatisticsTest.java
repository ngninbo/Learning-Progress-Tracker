package tracker.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tracker.service.statistics.Statistic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackerStatisticsTest {

    private static Statistic statistic;

    @BeforeAll
    static void setUp() {

        List<List<Long>> testData = List.of(
                List.of(182365L, 4L, 0L, 0L, 8L),
                List.of(182365L, 0L, 0L, 0L, 8L),
                List.of(182366L, 0L, 8L, 0L, 4L));

        statistic = TrackerTestUtil.init(testData);
    }

    @Test
    @DisplayName("Should return true when the most popular course is Spring")
    void findMostPopularCourses() {
        final List<String> mostPopularCourses = statistic.findMostPopularCourses();
        assertEquals(List.of("Spring"), mostPopularCourses);
    }

    @Test
    @DisplayName("Should return true when the least popular course is Databases")
    void findLeastPopularCourse() {
        assertEquals(List.of("Databases"), statistic.findLeastPopularCourse());
    }

    @Test
    @DisplayName("Should return true when Spring is the course with highest activity")
    void findCourseWithHighestActivity() {
        assertEquals(List.of("Spring"), statistic.findCourseWithHighestActivity());
    }

    @Test
    @DisplayName("Should return true when Spring is the course with highest activity")
    void findCourseWithLowestActivity() {
        assertEquals(List.of("Databases"), statistic.findCourseWithLowestActivity());
    }

    @Test
    @DisplayName("Should assert the hardest course to be Java")
    void findHardestCourse() {
        assertEquals(List.of("Java"), statistic.findHardestCourse());
    }

    @Test
    @DisplayName("Should assert the easiest course to be DSA")
    void findEasiestCourse() {
        assertEquals(List.of("DSA"), statistic.findEasiestCourse());
    }

}
