package tracker;

import tracker.builder.TrackerBuilder;

public class Main {

    public static void main(String[] args) {
        TrackerBuilder.init()
                .withTrackerController()
                .build()
                .start();
    }
}
