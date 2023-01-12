package tracker.command;

import tracker.Tracker;

public class TrackerListCommand implements Command {

    @Override
    public void execute() {
        list();
    }

    public void list() {
        if (Tracker.students.isEmpty()) {
            System.out.println("No students found");
            return;
        }

        System.out.println("Students:");
        Tracker.students.keySet().forEach(System.out::println);
    }
}
