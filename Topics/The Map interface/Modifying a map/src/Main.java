import java.util.*;

class MapUtils {

    public static void mapShare(Map<String, String> map) {
        // write your code here
        String value = map.get("a");

        if (value != null) {
            if (map.containsKey("b")) {
                map.replace("b", value);
            }

            map.put("b", value);
        }

        map.remove("c", map.get("c"));
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] pair = s.split(":");
            map.put(pair[0], pair[1]);
        }
        MapUtils.mapShare(map);
        map.forEach((key, value) -> System.out.println(key + ":" + value));
    }
}