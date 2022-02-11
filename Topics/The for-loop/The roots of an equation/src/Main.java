import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

class Main {

    private static int a;
    private static int b;
    private static int c;
    private static int d;
    private static final int MIN = 0;
    private static final int MAX = 1000;
    private static final int DEGREE = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        a = Integer.parseInt(scanner.nextLine());
        b = Integer.parseInt(scanner.nextLine());
        c = Integer.parseInt(scanner.nextLine());
        d = Integer.parseInt(scanner.nextLine());

        IntStream.rangeClosed(MIN, MAX)
                .filter(i -> isRoot().test(i))
                .forEach(System.out::println);
    }

    public static IntPredicate isRoot() {
        return i -> a * Math.pow(i, DEGREE) + b * Math.pow(i, DEGREE - 1) + i * c + d == 0;
    }
}