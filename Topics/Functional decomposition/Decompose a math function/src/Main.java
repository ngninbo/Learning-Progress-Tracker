import java.util.Locale;
import java.util.Scanner;

class MultipleFunction {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double x = scanner.nextDouble();
        System.out.println(f(x));
    }

    public static double f(double x) {
        return x <= 0 ? f1(x) : x < 1 ? f2(x) : f3(x);
    }

    public static double f1(double x) {
        return Math.pow(x, 2) + 1;
    }

    public static double f2(double x) {
        return  1 / Math.pow(x, 2);
    }

    public static double f3(double x) {
        return Math.pow(x, 2) - 1;
    }
}