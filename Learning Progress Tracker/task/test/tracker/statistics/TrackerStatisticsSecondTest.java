package tracker.statistics;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tracker.service.statistics.Statistic;
import tracker.utils.TrackerTestUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static tracker.utils.TrackerUtil.BASE_COURSES;

class TrackerStatisticsSecondTest {

    private static Statistic statistic;

    @BeforeAll
    static void setUp() {

        List<List<Long>> testData = List.of(
                List.of(10000L, 5L, 4L, 3L, 1L),
                List.of(10001L, 5L, 4L, 3L, 1L),
                List.of(10002L, 5L, 4L, 3L, 1L),
                List.of(10003L, 5L, 4L, 3L, 1L));

        statistic = TrackerTestUtil.init(testData);
    }

    @Test
    @DisplayName("Should assert that the most popular courses are Java, DSA, Databases and Spring")
    void findMostPopularCourses() {
        List<String> courses = statistic.findMostPopularCourses();
        assertEquals(BASE_COURSES, courses);
    }

    @Test
    @DisplayName("Should assert that the list least popular courses is empty")
    void findLeastPopularCourse() {
        List<String> courses = statistic.findLeastPopularCourse();
        courses.removeAll(statistic.findMostPopularCourses());
        assertEquals(List.of(), courses);
    }

    @Test
    @DisplayName("Should assert hardest course to be Spring")
    void findHardestCourse() {
        assertEquals(List.of("Spring"), statistic.findHardestCourse());
    }

    @Test
    @DisplayName("Should assert easiest course to Java")
    void findEasiestCourse() {
        List<String> courses = statistic.findEasiestCourse();
        courses.removeAll(statistic.findHardestCourse());
        assertEquals(List.of("Java"), courses);
    }

    @Test
    @DisplayName("Should assert all courses have the highest activity")
    void findCourseWithHighestActivity() {
        assertTrue(statistic.findCourseWithHighestActivity().containsAll(BASE_COURSES));
    }

    @Test
    @DisplayName("Should assert none course has the lowest activity")
    void findCourseWithLowestActivity() {
        List<String> courses = statistic.findCourseWithLowestActivity();
        courses.removeAll(statistic.findCourseWithHighestActivity());
        assertEquals(List.of(), courses);
    }
}