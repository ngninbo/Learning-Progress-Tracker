package tracker.utils;

public class StudentIdGenerator {

    private static final StudentIdGenerator instance = new StudentIdGenerator();

    private Long initialId = TrackerUtil.MIN_ID;

    private StudentIdGenerator() {
    }

    public static StudentIdGenerator getInstance() {
        return instance == null ? new StudentIdGenerator() : instance;
    }

    public Long getNextId() {
        incrementId();
        return initialId;
    }

    private void incrementId() {
        initialId++;
    }
}
