package tracker.builder;

import tracker.Tracker;
import tracker.model.Assignment;
import tracker.model.Student;
import tracker.notification.NotificationService;
import tracker.notification.NotificationServiceImpl;
import tracker.statistics.Statistic;
import tracker.utils.TrackerHelperFunction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TrackerBuilder {

    private Map<Long, Student> studentMap;
    private Map<String, Long> submits;
    private List<Assignment> assignmentList;
    private Statistic statistic;
    private NotificationService notificationService;

    private TrackerBuilder() {
    }

    public static TrackerBuilder init() {
        return new TrackerBuilder();
    }

    public TrackerBuilder withStudentTable() {
        this.studentMap = new LinkedHashMap<>();
        return this;
    }

    public TrackerBuilder withAssignmentList() {
        this.assignmentList = new ArrayList<>();
        return this;
    }

    public TrackerBuilder withInitialSubmit() {
        this.submits = TrackerHelperFunction.initialSubmission();
        return this;
    }

    public TrackerBuilder withNotificationService() {
        this.notificationService = new NotificationServiceImpl();
        return this;
    }

    public TrackerBuilder withStatistic() {
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

    public Tracker build() {
        return new Tracker(studentMap, submits, assignmentList, statistic, notificationService);
    }
}