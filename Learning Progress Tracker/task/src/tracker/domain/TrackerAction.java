package tracker.domain;

import java.util.Arrays;

public enum TrackerAction {

    BACK,

    ADD_STUDENTS,

    LIST,

    ADD_POINTS,

    FIND,

    STATISTICS,

    NOTIFY,

    EXIT,

    UNKNOWN;

    public static TrackerAction of(String input) {
        return Arrays.stream(values())
                .filter(trackerAction -> input.equalsIgnoreCase(trackerAction.name()))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
