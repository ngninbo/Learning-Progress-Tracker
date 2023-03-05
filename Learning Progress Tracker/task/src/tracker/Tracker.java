package tracker;

import tracker.command.Command;
import tracker.command.CommandFactory;
import tracker.domain.TrackerAction;
import tracker.model.Assignment;
import tracker.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tracker implements Runnable {

    private final Map<Long, Student> students = new HashMap<>();
    private final List<Assignment> assignments = new ArrayList<>();

    private TrackerAction action;

    @Override
    public void run() {
        start();
    }

    private void start() {
        System.out.println("Learning Progress Tracker");

        while(!exit()) {
            String input = Command.requestUserInput().trim().replace(" ", "_").toUpperCase();

            if (input.isEmpty()) {
                System.out.println("No input.");
            } else {
                action = TrackerAction.of(input);
                Command command = CommandFactory.getCommand(action, students, assignments);
                if (command != null) {
                    command.execute();
                }
            }
        }
    }

    private boolean exit() {
        return TrackerAction.EXIT.equals(action);
    }
}
