import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final long sum = scanner.tokens()
                .mapToLong(Integer::parseInt)
                .takeWhile(l -> l != 0).sum();
        System.out.println(sum);
    }
}