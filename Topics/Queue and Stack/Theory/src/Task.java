// You can experiment here, it won’t be checked

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Task {
  public static void main(String[] args) {
    Deque<Integer> deq = new LinkedList<>();

    for (int i = 10; i >= 0; i--) {
      deq.offerLast(i);
    }

    deq.pollFirst();
    deq.pollFirst();
    deq.pollLast();
    System.out.println(deq);

    deq = new LinkedList<>();
    deq.offerFirst(10);
    deq.peekFirst();
    deq.offerFirst(20);
    deq.offerLast(30);
    deq.peekLast();
    deq.pollFirst();
    deq.offerLast(40);
    System.out.println(deq);

    Deque<String> states = new ArrayDeque<String>();

    states.add("Germany");
    states.add("France");
    states.push("UK");
    states.offerLast("Norway");

    String sPop = states.pop();
    String sPeek = states.peek();
    String sPeekLast = states.peekLast();
    states.offer(sPop);
    String sPollLast = states.pollLast();

    while (states.peek() != null) {
      System.out.print(states.pop());
    }
  }
}
