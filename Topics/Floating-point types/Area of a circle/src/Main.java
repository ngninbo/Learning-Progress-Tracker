import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double radius = scanner.nextDouble();
        System.out.println(computeArea(radius));
    }

    private static double computeArea(double r) {
        return Math.PI * Math.pow(r, 2);
    }
}