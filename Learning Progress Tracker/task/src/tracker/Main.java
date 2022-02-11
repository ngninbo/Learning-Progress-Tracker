package tracker;

import tracker.builder.TrackerBuilder;

public class Main {

    public static void main(String[] args) {
        TrackerBuilder.init()
                .withStudentTable()
                .withInitialSubmit()
                .withAssignmentList()
                .withStatistic()
                .withNotificationService()
                .build()
                .start();
    }
}
