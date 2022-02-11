import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        // put your code here
        double d1 = scanner.nextDouble();
        double d2 = scanner.nextDouble();
        double deltaD = d2 - d1;

        System.out.println(deltaD);
    }
}