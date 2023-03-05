package tracker.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Category {

    MOST_POPULAR,
    LEAST_POPULAR,
    HIGHEST_ACTIVITY,
    LOWEST_ACTIVITY,
    EASIEST_COURSE,
    HARDEST_COURSE;

    public static List<Category> toList() {
        return Arrays.stream(values())
                .collect(Collectors.toList());
    }

    public String capitalize() {
        String name = replaceUnderscore();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }

    private String replaceUnderscore() {
        return name().replace("_", " ");
    }
}
