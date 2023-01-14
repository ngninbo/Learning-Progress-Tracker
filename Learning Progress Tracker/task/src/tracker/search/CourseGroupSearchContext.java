package tracker.search;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Find out which course has the highest and lowest student activity.
 * Higher student activity means a bigger number of completed tasks.
 * Find out which courses are the most and least popular ones.
 * The most popular has the biggest number of enrolled students;
 */
public class CourseGroupSearchContext implements SearchStrategy {

    private Map<String, Long> map;

    public CourseGroupSearchContext setMap(Map<String, Long> map) {
        this.map = map;
        return this;
    }

    @Override
    public List<String> findMax() {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), Collections.max(map.values())))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findMin() {
        return map.entrySet()
                .stream().filter(entry -> Objects.equals(entry.getValue(), Collections.min(map.values())))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
