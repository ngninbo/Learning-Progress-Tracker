import java.util.Scanner;

class Main {

    private static final double GRAVITY = 9.8;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double rho = Double.parseDouble(scanner.next());
        double h = Double.parseDouble(scanner.next());

        double pressure = rho * GRAVITY * h;
        System.out.println(pressure);
    }
}