import java.util.Scanner;

class Main {

    private static final double FACTOR = 1.8;
    private static final double THERM = 32;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double temperatureInCelsius = scanner.nextDouble();
        double temperatureInFahrenheit = convertToFahrenheit(temperatureInCelsius);
        System.out.println(temperatureInFahrenheit);
    }

    private static double convertToFahrenheit(double temperatureInCelsius) {
        return temperatureInCelsius * FACTOR + THERM;
    }
}