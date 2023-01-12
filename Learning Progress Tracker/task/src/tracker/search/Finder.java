package tracker.search;

import java.util.List;

public class Finder {

    private SearchStrategy strategy;

    public Finder setStrategy(SearchStrategy strategy) {
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
