import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        IntStream.rangeClosed(0, size)
                .mapToObj(i -> scanner.nextLine())
                .collect(Collectors.toCollection(TreeSet::new))
                .forEach(System.out::println);
    }
}