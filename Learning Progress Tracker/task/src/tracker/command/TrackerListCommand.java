package tracker.command;

import tracker.model.Student;

import java.util.Map;

public class TrackerListCommand implements Command {

    public Map<Long, Student> students;

    public TrackerListCommand(Map<Long, Student> students) {
        this.students = students;
    }

    @Override
    public void execute() {
        list();
    }

    public void list() {
        if (students.isEmpty()) {
            System.out.println("No students found");
            return;
        }

        System.out.println("Students:");
        students.keySet().forEach(System.out::println);
    }
}
