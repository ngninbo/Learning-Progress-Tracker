package tracker.command;

import tracker.domain.Category;
import tracker.model.Student;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    default String completion(Student student, String course) {
        return student.getId() + "\t" +
                sumPoints(student, course) + "\t" +
                progress(student, course) + "%\n";
    }

    default Long sumPoints(Student student, String course) {
        return student.getCourses().get(course).getPoints();
    }

    private double progress(Student student, String course) {
        return BigDecimal.valueOf(sumPoints(student, course))
                .multiply(BigDecimal.TEN.multiply(BigDecimal.TEN))
                .divide(BigDecimal.valueOf(student.maxPoints(course)), 1, RoundingMode.HALF_UP)
                .doubleValue();
    }

    default String findCourseByCategory(Category category) {
        String stringBuilder = stringifyCourseList(findByCategory(category));

        return stringBuilder.isEmpty() ? "n/a" : stringBuilder;
    }

    private String stringifyCourseList(List<String> courses) {
        StringBuilder sb;
        sb = new StringBuilder();
        for (int i = 0; i < courses.size(); i++) {
            sb.append(courses.get(i));
            if (i < courses.size() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
