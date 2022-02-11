import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main {

    private static Map<String, String> codeMap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] characters = scanner.nextLine().split("");
        String[] codes = scanner.nextLine().split("");

        codeMap = IntStream.range(0, codes.length)
                .boxed()
                .collect(Collectors.toMap(i -> characters[i], i -> codes[i], (a, b) -> b, LinkedHashMap::new));

        String text = scanner.nextLine();
        String cipher = scanner.nextLine();

        encode(text);
        decode(cipher);
    }

    private static void decode(String cipher) {
        String[] codes =  cipher.split("");
        Arrays.stream(codes).forEach(code -> codeMap.forEach((key, value) -> {
            if (code.equals(value)) {
                System.out.print(key);
            }
        }));
        System.out.println();
    }

    private static void encode(String text) {
        Arrays.stream(text.split(""))
                .map(s -> codeMap.get(s))
                .forEach(System.out::print);
        System.out.println();
    }
}