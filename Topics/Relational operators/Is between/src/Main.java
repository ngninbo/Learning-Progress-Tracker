import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        int lower = scanner.nextInt();
        int upper = scanner.nextInt();

        System.out.println(isBetween(target, lower, upper));
    }

    private static boolean isBetween(int target, int lower, int upper) {
        return (lower <= upper) ? ((target >= lower) && (target <= upper)) : ((target <= lower) && (target >= upper));
    }
}