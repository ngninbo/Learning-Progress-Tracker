import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] inputs = scanner.nextLine().split("\\s+");

        int[] numbers = Arrays.stream(inputs).mapToInt(Integer::parseInt).toArray();

        int rotation = scanner.nextInt();

        int[] resultingArray = doRightRotation(numbers, rotation);
        printResult(resultingArray);
    }

    private static void printResult(int[] resultingArray) {
        for (int number : resultingArray) {
            System.out.print(number + " ");
        }
    }

    private static int[] doRightRotation(int[] numbers, int rotation) {

        List<Integer> resultingArray = new ArrayList<>();
        int length = numbers.length;
        int opt = (rotation % length);

        for (int i = length - opt; i < length; i++) {
            resultingArray.add(numbers[i]);
        }

        for (int i = 0; i < length - opt; i++) {
            resultingArray.add(numbers[i]);
        }

        return resultingArray.stream().mapToInt(i -> i).toArray();
    }

}
