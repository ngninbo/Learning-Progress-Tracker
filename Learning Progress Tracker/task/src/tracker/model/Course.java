package tracker.model;

import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Course {

    @Getter
    private String name;
    @Getter
    private long maxPoints;
    @Getter
    private long points;
    @Getter
    private Set<CourseStatus> statuses = new HashSet<>();

    public Course(String name) {
        this.name = name;
        this.points = 0;
        this.statuses = new HashSet<>();
        this.maxPoints = CourseType.maxPoints(name);
    }

    public Course(String name, long points) {
        this(name);
        this.points = points;
    }

    public void updatePoints(long points) {
        this.points += points;
    }

    public boolean isCompleted() {
        return Objects.equals(points, maxPoints);
    }

    public void updateStatus(CourseStatus status) {
        statuses.add(status);
    }
}
