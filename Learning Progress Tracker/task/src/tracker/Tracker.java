package tracker;

import tracker.model.Student;
import tracker.service.TrackerService;

import java.util.*;

import static tracker.utils.TrackerUtil.*;
public class Tracker {

    private final TrackerService trackerService;

    public Tracker(TrackerService trackerService) {
        this.trackerService = trackerService;
    }

    public TrackerService getTrackerService() {
        return trackerService;
    }

    public void start() {
        System.out.println(PROGRAM_TITLE);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(NO_INPUT);
            } else {
                switch (input) {
                    case EXIT_CMD:
                        System.out.println(OUTPUT_MSG_ON_EXIT);
                        exit = true;
                        break;
                    case STUDENT_LIST_CMD:
                        trackerService.showOverview();
                        break;
                    case ADD_STUDENTS_CMD:
                        trackerService.addStudents();
                        break;
                    case ADD_POINTS_CMD:
                        trackerService.addPoints();
                        break;
                    case FIND_STUDENT_CMD:
                        trackerService.findStudent();
                        break;
                    case STATISTICS_CMD:
                        trackerService.showStatistics();
                        break;
                    case NOTIFICATION_CMD:
                        trackerService.sendNotificationMessage();
                        break;
                    case BACK_CMD:
                        System.out.println(ENTER_EXIT_CMD);
                        break;
                    default:
                        System.out.println(ERROR_UNKNOWN_COMMAND);

                }
            }
        }
    }

    public Map<Long, Student> getStudentMap() {
        return trackerService.getStudentMap();
    }
}
