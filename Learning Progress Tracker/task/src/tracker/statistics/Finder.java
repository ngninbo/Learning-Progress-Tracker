package tracker.statistics;

import java.util.List;

public class Finder {

    private FindStrategy strategy;

    public Finder setStrategy(FindStrategy strategy) {
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
