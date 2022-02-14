package tracker.builder;

import tracker.service.TrackerController;
import tracker.model.Assignment;
import tracker.model.Student;
import tracker.service.notification.NotificationService;
import tracker.service.notification.NotificationServiceImpl;
import tracker.service.statistics.Statistic;
import tracker.utils.TrackerHelperFunction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TrackerControllerBuilder {

    private Map<Long, Student> studentMap;
    private Map<String, Long> submits;
    private List<Assignment> assignmentList;
    private Statistic statistic;
    private NotificationService notificationService;

    private TrackerControllerBuilder() {
    }

    public static TrackerControllerBuilder init() {
        return new TrackerControllerBuilder();
    }

    public TrackerControllerBuilder withStudentTable() {
        this.studentMap = new LinkedHashMap<>();
        return this;
    }

    public TrackerControllerBuilder withAssignmentList() {
        this.assignmentList = new ArrayList<>();
        return this;
    }

    public TrackerControllerBuilder withInitialSubmit() {
        this.submits = TrackerHelperFunction.initialSubmission();
        return this;
    }

    public TrackerControllerBuilder withNotificationService() {
        this.notificationService = new NotificationServiceImpl();
        return this;
    }

    public TrackerControllerBuilder withStatistic() {
        this.statistic = TrackerAnalyserBuilder.init()
                .withStudentMap(studentMap)
                .withCourseSubmissions(submits)
                .withAssignmentList(assignmentList)
                .withFinder()
                .withSearchContext()
                .withStrengthSearchContext()
                .build();

        return this;
    }

    public TrackerController build() {
        return new TrackerController(studentMap, submits, assignmentList, statistic, notificationService);
    }
}
