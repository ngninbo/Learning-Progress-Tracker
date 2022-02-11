import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lower = Integer.parseInt(scanner.nextLine());
        int upper = Integer.parseInt(scanner.nextLine());

        double avg = IntStream.rangeClosed(lower, upper)
                .filter(i -> i % 3 == 0)
                .mapToDouble(i -> (double) i)
                .average()
                .orElse(0);

        System.out.println(avg);
    }
}