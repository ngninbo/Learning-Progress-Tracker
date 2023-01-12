import java.util.ArrayList;
import java.util.LinkedList;

class ListOperations {
    public static void changeHeadsAndTails(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        linkedList.addLast(arrayList.set(arrayList.size() - 1, linkedList.pollLast()));
        linkedList.addFirst(arrayList.set(0, linkedList.pollFirst()));
    }
}