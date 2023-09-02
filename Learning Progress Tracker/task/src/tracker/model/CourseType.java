package tracker.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CourseType {

    JAVA (Points.MAX_POINTS_JAVA),
    DSA (Points.MAX_POINTS_DSA),
    DATABASES (Points.MAX_POINTS_DATABASES),
    SPRING (Points.MAX_POINTS_SPRING);

    private final long maxPoints;

    CourseType(long maxPoints) {
        this.maxPoints = maxPoints;
    }

    public static long maxPoints(String name) {
        return Arrays.stream(values())
                .filter(type -> name.equalsIgnoreCase(type.name()))
                .map(CourseType::getMaxPoints)
                .findFirst()
                .orElse(0L);
    }

    public static String get(int i) {
        return values()[i].capitalize();
    }

    public long getMaxPoints() {
        return maxPoints;
    }

    public static boolean noneMatch(String name) {
        return names().stream().noneMatch(s -> s.equalsIgnoreCase(name));
    }

    public String capitalize() {
        if (this == CourseType.DSA) {
            return name();
        }
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

    public static List<String> names() {
        return Arrays.stream(values())
                .map(CourseType::capitalize)
                .collect(Collectors.toList());
    }

    private static class Points {
        public static final long MAX_POINTS_JAVA = 600;
        public static final long MAX_POINTS_DSA = 400;
        public static final long MAX_POINTS_DATABASES = 480;
        public static final long MAX_POINTS_SPRING = 550;
    }
}
