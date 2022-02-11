import java.util.*;

public class Main {

    public static void main(String[] args) {
        final int magic = 7;
        Deque<Integer> queue = new ArrayDeque<>(List.of(2, 0, 1, magic));
        System.out.println(queue);
    }
}