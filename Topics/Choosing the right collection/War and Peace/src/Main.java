import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Map<String, Integer> wordAmountMap = new LinkedHashMap<>();
        Scanner scanner = new Scanner(System.in);
        List<String> words = Arrays.stream(scanner.nextLine().split("\\s"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        words.forEach(word -> {
            if (!wordAmountMap.containsKey(word)) {
                wordAmountMap.put(word, 1);
            } else {
                wordAmountMap.replace(word, wordAmountMap.get(word) + 1);
            }
        });

        wordAmountMap.forEach((s, integer) -> System.out.printf("%s %d\n", s, integer));
    }
}