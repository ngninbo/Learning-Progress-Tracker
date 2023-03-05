package tracker.command;

import tracker.domain.Category;

import java.util.List;

public interface Statistic {

    List<String> findMostPopularCourses();
    List<String> findLeastPopularCourse();
    List<String> findCourseWithHighestActivity();
    List<String> findCourseWithLowestActivity();
    List<String> findEasiestCourse();
    List<String> findHardestCourse();

    default List<String> findByCategory(Category category) {
        List<String> courses;

        switch (category) {
            case MOST_POPULAR:
                return findMostPopularCourses();
            case LEAST_POPULAR:
                courses = findLeastPopularCourse();
                courses.removeAll(findMostPopularCourses());
                return courses;
            case HIGHEST_ACTIVITY:
                return findCourseWithHighestActivity();
            case LOWEST_ACTIVITY:
                courses = findCourseWithLowestActivity();
                courses.removeAll(findCourseWithHighestActivity());
                return courses;
            case EASIEST_COURSE:
                return findEasiestCourse();
            case HARDEST_COURSE:
                courses = findHardestCourse();
                courses.removeAll(findEasiestCourse());
                return courses;
            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }
    }
}
