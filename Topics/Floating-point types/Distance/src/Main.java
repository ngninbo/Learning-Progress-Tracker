import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double distance = scanner.nextInt();
        double time = scanner.nextInt();

        double speed = distance / time;

        System.out.println(speed);
    }
}