package tracker.search;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public interface SearchStrategy {

    List<String> findMax();
    List<String> findMin();

    default <T> List<String> filter(Map<String, T> map, T t) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), t))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
