import java.util.Scanner;

class Main {

    public static final int A = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble();
        double polyValue = Math.pow(x, A) + Math.pow(x, A - 1) + x + 1;

        System.out.println(polyValue);
    }
}