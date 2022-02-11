import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

class Main {

    public static final int FACTOR_THREE = 3;
    public static final int FACTOR_FIVE = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lower = Integer.parseInt(scanner.next());
        int upper = Integer.parseInt(scanner.next());

        IntStream.rangeClosed(lower, upper)
                .forEach(i -> {
                    if (isMultiple(FACTOR_THREE).and(isMultiple(FACTOR_FIVE)).test(i)) {
                        System.out.println("FizzBuzz");
                    } else if (isMultiple(FACTOR_THREE).test(i)) {
                        System.out.println("Fizz");
                    } else if (isMultiple(FACTOR_FIVE).test(i)) {
                        System.out.println("Buzz");
                    } else {
                        System.out.println(i);
                    }
                });
    }

    private static IntPredicate isMultiple(int n) {
        return i -> i % n == 0;
    }
}