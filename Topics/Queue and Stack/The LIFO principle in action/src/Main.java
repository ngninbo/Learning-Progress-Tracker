import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfElement = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();
        IntStream.range(0, numberOfElement)
                .forEach(i -> stack.push(scanner.nextInt()));
        IntStream.range(0, numberOfElement)
                .forEach(i -> System.out.println(stack.pop()));
    }
}