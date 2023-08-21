package tracker;

import tracker.command.Command;
import tracker.command.CommandFactory;
import tracker.domain.TrackerAction;
import tracker.model.Assignment;
import tracker.model.Student;

import java.util.*;

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
                Optional.ofNullable(CommandFactory.getCommand(action, students, assignments))
                        .ifPresent(Command::execute);
            }
        }
    }

    private boolean exit() {
        return TrackerAction.EXIT.equals(action);
    }
}
