import java.util.stream.IntStream;

class TestHelper {

    public static final int START_INCLUSIVE = 10;
    public static final int END_INCLUSIVE = 1000;
    public static final int LIMIT = 45;

    public static int[] primeGenerator() {
        return  IntStream.rangeClosed(START_INCLUSIVE, END_INCLUSIVE)
                .filter(TestHelper::isPrime)
                .limit(LIMIT)
                .toArray();
    }

    private static boolean isPrime(int n) {
        return IntStream.iterate(2, i -> i < n, i -> i + 1)
                .allMatch(i -> n % i != 0);
    }
}