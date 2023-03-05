package tracker.search;

import java.util.List;

public class SearchContext {

    private SearchStrategy strategy;

    public SearchContext setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    public List<String> findMax() {
        return strategy.findMax();
    }

    public List<String> findMin() {
        return strategy.findMin();
    }
}
