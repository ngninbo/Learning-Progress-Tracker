package tracker.utils;

public enum CourseOption {

    JAVA (600),
    DSA (400),
    DATABASES (480),
    SPRING (550);

    CourseOption(final long maxPoints) {
        this.maxPoints = maxPoints;
    }

    private final long maxPoints;

    public long getMaxPoints() {
        return maxPoints;
    }
}
