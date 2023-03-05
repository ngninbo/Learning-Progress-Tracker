package tracker.domain;

public enum Category {

    MOST_POPULAR,
    LEAST_POPULAR,
    HIGHEST_ACTIVITY,
    LOWEST_ACTIVITY,
    EASIEST_COURSE,
    HARDEST_COURSE;

    public String capitalize() {
        String name = replaceUnderscore();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }

    private String replaceUnderscore() {
        return name().replace("_", " ");
    }
}
