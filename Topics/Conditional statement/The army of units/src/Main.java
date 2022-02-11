import java.util.Scanner;

class Main {

    public static final int LEGION_MIN = 1000;
    public static final int ZOUNDS_MIN = 250;
    public static final int THRONG_MIN = 20;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int units = scanner.nextInt();

        System.out.println(classifyArmy(units));
    }

    private static String classifyArmy(int units) {

        if (units >= LEGION_MIN) {
            return "legion";
        } else if (units >= ZOUNDS_MIN) {
            return "zounds";
        } else if (units >= THRONG_MIN) {
            return "throng";
        } else if (units >= 1) {
            return "pack";
        } else {
            return "no army";
        }
    }
}