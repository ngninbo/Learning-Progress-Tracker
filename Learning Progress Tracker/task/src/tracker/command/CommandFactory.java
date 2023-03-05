package tracker.command;

import tracker.domain.TrackerAction;
import tracker.model.Assignment;
import tracker.model.Student;

import java.util.List;
import java.util.Map;

public class CommandFactory {

    public static Command getCommand(TrackerAction option, Map<Long, Student> studentList, List<Assignment> assignments) {

        switch (option) {
            case BACK:
                return new TrackerBackCommand();
            case ADD_STUDENTS:
                return new TrackerAddStudentCommand(studentList);
            case LIST:
                return new TrackerListCommand(studentList);
            case ADD_POINTS:
                return new TrackerAddPointCommand(studentList, assignments);
            case FIND:
                return new TrackerFindCommand(studentList);
            case STATISTICS:
                return new TrackerStatisticsCommand(studentList, assignments);
            case NOTIFY:
                return new TrackerNotifyCommand(studentList);
            case EXIT:
                return new TrackerExitCommand();
            default:
                System.out.println("Error: unknown command!");
                break;
        }

        return null;
    }
}
