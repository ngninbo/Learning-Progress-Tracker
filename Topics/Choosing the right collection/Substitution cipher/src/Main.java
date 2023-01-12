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

        System.out.println(encode(text));
        System.out.println(decode(cipher));
    }

    private static String decode(String cipher) {
        String[] codes =  cipher.split("");
        StringBuilder sb = new StringBuilder();
        Arrays.stream(codes).forEach(code -> codeMap.forEach((key, value) -> {
            if (code.equals(value)) {
                sb.append(key);
            }
        }));
        return sb.toString();
    }

    private static String encode(String text) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(text.split(""))
                .map(s -> codeMap.get(s))
                .forEach(sb::append);
        return sb.toString();
    }
}