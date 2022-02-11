import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeOfArray = scanner.nextInt();
        int[] array = IntStream.range(0, sizeOfArray)
                .map(i -> Integer.parseInt(scanner.next()))
                .toArray();

        int target = scanner.nextInt();

        System.out.println(Arrays.stream(array).anyMatch(i -> target == i));
    }
}