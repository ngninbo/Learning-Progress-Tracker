package tracker.search;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public interface MapFilter<T> {

    List<String> filterBy(T value);

    default List<String> filter(Map<String, T> map, T t) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), t))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
