package tracker.domain;

public class NumberGenerator {

    private static final NumberGenerator INSTANCE = new NumberGenerator();

    private int value = 9999;

    public static NumberGenerator getInstance() {
        return INSTANCE;
    }

    public int next() {
        return ++value;
    }
}
