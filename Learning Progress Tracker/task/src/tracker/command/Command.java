package tracker.command;

import tracker.domain.TrackerAction;

import java.util.Scanner;
import java.util.function.Predicate;

public interface Command {
    void execute();

    default Predicate<String> back() {
        return s -> TrackerAction.BACK.name().equalsIgnoreCase(s);
    }

    static String requestUserInput() {
        return new Scanner(System.in).nextLine();
    }
}
