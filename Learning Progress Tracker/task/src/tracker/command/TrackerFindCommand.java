package tracker.command;

import tracker.Tracker;
import tracker.model.Student;
import tracker.util.TrackerUtil;

public class TrackerFindCommand implements Command {

    @Override
    public void execute() {
        find();
    }

    private void find() {
        System.out.println("Enter an id or 'back' to return");
        while (true) {
            String input = TrackerUtil.requestUserInput();

            if ("back".equals(input)) {
                return;
            }

            if (input.matches("\\d+")) {
                Student student = Tracker.students.get(Long.parseLong(input));
                if (student == null) {
                    System.out.printf("No student is found for id=%s\n", input);
                } else {
                    System.out.println(TrackerUtil.printCoursePoints(student));
                }
            }
        }
    }
}
