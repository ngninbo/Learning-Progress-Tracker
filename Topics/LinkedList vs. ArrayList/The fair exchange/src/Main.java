import java.util.ArrayList;
import java.util.LinkedList;

class ListOperations {
    public static void changeHeadsAndTails(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        final String linkedListFirst = linkedList.getFirst();
        final String linkedListLast = linkedList.getLast();
        final String arrayListFirst = arrayList.get(0);
        final int arrayListLastIndex = arrayList.size() - 1;
        final String arrayListLast = arrayList.get(arrayListLastIndex);

        arrayList.set(0, linkedListFirst);
        arrayList.set(arrayListLastIndex, linkedListLast);

        linkedList.set(0, arrayListFirst);
        linkedList.set(linkedList.size() - 1, arrayListLast);
    }
}