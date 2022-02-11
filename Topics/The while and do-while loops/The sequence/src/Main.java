import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

class Main {

    public static final int FACTOR = 4;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfElement = scanner.nextInt();
        int max = IntStream.range(0, numberOfElement)
                .map(i -> scanner.nextInt())
                .filter(isMultiple())
                .distinct()
                .max().orElse(0);

        System.out.println(max);
    }

    private static IntPredicate isMultiple() {
        return i -> i % FACTOR == 0;
    }
}