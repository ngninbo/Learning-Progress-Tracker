package tracker.domain;

import tracker.command.*;

public class CommandFactory {

    public static Command getCommand(String input) {

        switch (input) {
            case "back":
                return new TrackerBackCommand();
            case "add students":
                return new TrackerAddStudentCommand();
            case "list":
                return new TrackerListCommand();
            case "add points":
                return new TrackerAddPointCommand();
            case "find":
                return new TrackerFindCommand();
            case "statistics":
                return new TrackerStatisticsCommand();
            case "notify":
                return new TrackerNotifyCommand();
            case "exit":
                return new TrackerExitCommand();
            default:
                System.out.println("Error: unknown command!");
                break;
        }

        return null;
    }
}
