package tracker.search;

import java.util.List;

public interface SearchStrategy {

    List<String> findMax();
    List<String> findMin();
}
