package tracker.command;

import tracker.domain.CourseType;
import tracker.domain.TrackerStatistics;
import tracker.model.Assignment;
import tracker.model.Student;

import java.util.*;

public class TrackerStatisticsCommand extends TrackerStatistics implements Command {

    public TrackerStatisticsCommand(Map<Long, Student> studentMap, List<Assignment> assignments) {
        super(studentMap, assignments);
    }

    @Override
    public void execute() {
        stat();
    }

    private void stat() {
        System.out.println("Type the name of a course to see details or 'back' to quit");
        statistics();

        while (true) {
            String courseName = new Scanner(System.in).nextLine();
            if (back().test(courseName)) {
                return;
            } else if (CourseType.noneMatch(courseName)) {
                System.out.println("Unknown course.");
            } else {
                showTopLearners(CourseType.valueOf(courseName.toUpperCase()));
            }
        }
    }
}
