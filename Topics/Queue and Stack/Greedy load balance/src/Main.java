import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Deque<Integer> firstQueue = new ArrayDeque<>();
        int totalLoadFirstQueue = 0;
        Deque<Integer> secondQueue = new ArrayDeque<>();
        int totalLoadSecondQueue = 0;
        Scanner scanner = new Scanner(System.in);

        int numberOfTasks = scanner.nextInt();

        for (int i = 0; i < numberOfTasks; i++) {
            int id = Integer.parseInt(scanner.next());
            int load = Integer.parseInt(scanner.next());
            if (totalLoadFirstQueue < totalLoadSecondQueue || totalLoadFirstQueue == totalLoadSecondQueue) {
                firstQueue.offer(id);
                totalLoadFirstQueue += load;
            } else {
                secondQueue.offer(id);
                totalLoadSecondQueue += load;
            }
        }

        firstQueue.forEach(integer -> System.out.print(integer + " "));
        System.out.println();
        secondQueue.forEach(integer -> System.out.print(integer + " "));
    }
}