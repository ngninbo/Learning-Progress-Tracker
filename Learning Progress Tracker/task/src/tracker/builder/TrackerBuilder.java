package tracker.builder;

import tracker.Tracker;
import tracker.service.TrackerController;

public class TrackerBuilder {

    private TrackerController trackerController;

    private TrackerBuilder() {
    }

    public static TrackerBuilder init() {
        return new TrackerBuilder();
    }

    public TrackerBuilder withTrackerController() {
        this.trackerController = TrackerControllerBuilder.init()
                .withStudentTable()
                .withInitialSubmit()
                .withAssignmentList()
                .withStatistic()
                .withNotificationService()
                .build();

        return this;

    }

    public Tracker build() {
        return new Tracker(trackerController);
    }
}