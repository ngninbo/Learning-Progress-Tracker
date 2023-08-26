package tracker.search;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CourseStrengthSearch implements SearchStrategy {

    private final Map<String, Double> map;

    public CourseStrengthSearch(Map<String, Double> map) {
        this.map = map;
    }

    @Override
    public List<String> findMax() {
        return filter(map, Collections.max(map.values()));
    }

    @Override
    public List<String> findMin() {
        return filter(map, Collections.min(map.values()));
    }
}
