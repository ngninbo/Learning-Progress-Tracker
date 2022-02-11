import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfElements = Integer.parseInt(scanner.nextLine());
        int idxFirstMax = 0;

        List<Integer> numbers = IntStream.range(0, numberOfElements)
                .mapToObj(i -> Integer.parseInt(scanner.next()))
                .collect(Collectors.toCollection(LinkedList::new));

        int max = numbers.get(0);

        for (int i = 0; i < numberOfElements; i++) {
            int number = numbers.get(i);
            if (number > max) {
                idxFirstMax = i;
                break;
            }
        }

        System.out.println(idxFirstMax);
    }
}