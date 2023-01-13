package tracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tracker.util.TrackerUtil;

import java.util.Objects;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Course {

    private String name;
    private long maxPoints;
    private long points;
    private boolean notified;

    public Course(String name) {
        this.name = name;
        this.maxPoints = TrackerUtil.maxPoints(name);
    }

    public Course(String name, long points) {
        this(name);
        this.points = points;
    }

    public void updatePoints(long points) {
        this.points += points;
    }

    public boolean isEnrolled() {
        return points > 0;
    }

    public boolean isCompleted() {
        return Objects.equals(points, maxPoints);
    }
}
