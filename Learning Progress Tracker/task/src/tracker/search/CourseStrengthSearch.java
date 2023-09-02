package tracker.search;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CourseStrengthSearch implements MapFilter<Double>, SearchStrategy {

    private final Map<String, Double> map;

    public CourseStrengthSearch(Map<String, Double> map) {
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

    @Override
    public List<String> filterBy(Double value) {
        return filter(map, value);
    }
}
