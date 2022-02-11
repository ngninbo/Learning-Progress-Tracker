import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Deque<Integer> integers = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);
        int numberOfElements = Integer.parseInt(scanner.nextLine());

        while (numberOfElements > 0) {
            int number = Integer.parseInt(scanner.nextLine());

            if (number % 2 == 0) {
                integers.offerFirst(number);
            } else {
                integers.offerLast(number);
            }

            numberOfElements--;
        }

        integers.forEach(System.out::println);
    }
}