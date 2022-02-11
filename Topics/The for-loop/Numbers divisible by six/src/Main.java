import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

class Main {

    public static final int FACTOR = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int limit = Integer.parseInt(scanner.nextLine());

        long sum = IntStream.range(0, limit)
                .map(i -> scanner.nextInt())
                .filter(isMultiple())
                .sum();

        System.out.println(sum);
    }

    private static IntPredicate isMultiple() {
        return i -> i % FACTOR == 0;
    }
}