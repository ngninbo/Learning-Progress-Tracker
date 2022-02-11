import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lower = Integer.parseInt(scanner.next());
        int upper = Integer.parseInt(scanner.next());

        long prod = IntStream.range(lower, upper).reduce(1, (a, b) -> a * b);
        System.out.println(prod);
    }
}