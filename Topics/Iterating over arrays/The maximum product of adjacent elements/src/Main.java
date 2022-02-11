import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int limit = Integer.parseInt(scanner.nextLine());

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        int max = IntStream.range(0, limit)
                .mapToObj(i -> i - 1 < 0 ? numbers.get(i) : Integer.valueOf(numbers.get(i - 1) * numbers.get(i)))
                .max(Integer::compare).orElse(0);

        System.out.println(max);
    }
}