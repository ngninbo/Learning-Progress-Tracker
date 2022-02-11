import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double a = Double.parseDouble(scanner.next());
        double b = Double.parseDouble(scanner.next());
        double c = Double.parseDouble(scanner.next());

        double x = findX(a, b, c);

        System.out.println(x);
    }

    private static double findX(double a, double b, double c) {
        return (c - b) / a;
    }
}