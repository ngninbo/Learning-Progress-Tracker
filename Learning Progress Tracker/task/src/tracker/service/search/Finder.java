package tracker.service.search;

import java.util.List;

public class Finder {

    private SearchService strategy;

    public Finder setStrategy(SearchService strategy) {
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
