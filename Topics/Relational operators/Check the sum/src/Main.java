import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        boolean result = checkSum(a, b, c);
        System.out.println(result);
    }

    private static boolean checkSum(int a, int b, int c) {
        return a + b == 20 || a + c == 20 || b + c == 20;
    }
}