import java.util.Locale;
import java.util.Scanner;

class Main {

    private static final double B1 = 10.5;
    private static final double B2 = 4.4;
    private static final double B3 = 2.2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double d = scanner.nextDouble();

        double result = evalEx(a, b, c, d);
        System.out.println(result);
    }

    private static double evalEx(double a, double b, double c, double d) {
        return a * B1 + b * B2 + (c + d) / B3;
    }
}