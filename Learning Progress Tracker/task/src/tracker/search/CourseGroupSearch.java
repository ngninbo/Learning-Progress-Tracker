package tracker.search;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Find out which course has the highest and lowest student activity.
 * Higher student activity means a bigger number of completed tasks.
 * Find out which courses are the most and least popular ones.
 * The most popular has the biggest number of enrolled students;
 */
public class CourseGroupSearch implements SearchStrategy {

    private final Map<String, Long> map;

    public CourseGroupSearch(Map<String, Long> map) {
        this.map = map;
    }

    @Override
    public List<String> findMax() {
        return filterBy(Collections.max(map.values()));
    }

    @Override
    public List<String> findMin() {
        return filterBy(Collections.min(map.values()));
    }

    private List<String> filterBy(Long value) {
        return filter(map, value);
    }
}
