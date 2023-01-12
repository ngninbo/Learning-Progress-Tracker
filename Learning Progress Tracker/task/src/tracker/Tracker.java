package tracker;

import tracker.command.Command;
import tracker.domain.CommandFactory;
import tracker.model.Assignment;
import tracker.model.Student;
import tracker.util.TrackerUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tracker implements Serializable {

    private static final Tracker INSTANCE = new Tracker();

    public static Map<Long, Student> students = new HashMap<>();
    public static List<Assignment> assignments = new ArrayList<>();

    public static Tracker getInstance() {
        return INSTANCE;
    }

    public void start() {
        System.out.println("Learning Progress Tracker");
        String input = null;

        while(!"exit".equals(input)) {
            input = TrackerUtil.requestUserInput().trim();

            if (input.isEmpty()) {
                System.out.println("No input.");
            } else {
                Command command = CommandFactory.getCommand(input);
                if (command != null) {
                    command.execute();
                }
            }
        }
    }
}
