import java.util.ArrayList;
import java.util.LinkedList;

class ListOperations {
    public static void mergeLists(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        arrayList.forEach(linkedList::addLast);
        System.out.printf("The new size of LinkedList is %d\n", linkedList.size());
    }
}