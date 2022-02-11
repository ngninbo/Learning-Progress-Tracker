// You can experiment here, it won’t be checked

import java.util.LinkedList;
import java.util.Queue;

public class Task {
  public static void main(String[] args) {
    Queue<Integer> q = new LinkedList<>();
    q.offer(100);
    q.offer(200);
    q.peek();
    q.offer(300);
    q.poll();
    q.offer(400);
    q.peek();
    System.out.println(q);
  }
}
