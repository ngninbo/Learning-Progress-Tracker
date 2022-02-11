import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lower = Integer.parseInt(scanner.next());
        int upper = Integer.parseInt(scanner.next());
        int factor = Integer.parseInt(scanner.next());

        long sumMultiple = IntStream.rangeClosed(lower, upper)
                .filter(isMultipleOf(factor))
                .count();

        System.out.println(sumMultiple);
    }

    private static IntPredicate isMultipleOf(int factor) {
        return i -> i % factor == 0;
    }
}