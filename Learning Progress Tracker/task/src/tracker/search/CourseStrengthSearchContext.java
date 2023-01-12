package tracker.search;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseStrengthSearchContext implements SearchStrategy {

    private Map<String, Double> map;

    public CourseStrengthSearchContext setMap(Map<String, Double> map) {
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
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), Collections.min(map.values())))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
