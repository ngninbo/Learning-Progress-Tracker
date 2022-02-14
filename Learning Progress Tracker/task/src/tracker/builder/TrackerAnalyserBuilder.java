package tracker.builder;

import tracker.model.Assignment;
import tracker.model.Student;
import tracker.service.search.CourseGroupSearchContext;
import tracker.service.search.CourseStrengthSearchContext;
import tracker.service.search.Finder;
import tracker.service.statistics.TrackerStatistics;

import java.util.List;
import java.util.Map;

public class TrackerAnalyserBuilder {

    private Map<Long, Student> studentMap;
    private Map<String, Long> courseSubmissions;
    private List<Assignment> assignments;
    private Finder finder;
    private CourseGroupSearchContext searchContext;
    private CourseStrengthSearchContext strengthSearchContext;

    private TrackerAnalyserBuilder() {
    }

    public static TrackerAnalyserBuilder init() {
        return new TrackerAnalyserBuilder();
    }

    public TrackerAnalyserBuilder withStudentMap(Map<Long, Student> studentMap) {
        this.studentMap = studentMap;
        return this;
    }

    public TrackerAnalyserBuilder withCourseSubmissions(Map<String, Long> courseSubmissions) {
        this.courseSubmissions = courseSubmissions;
        return this;
    }

    public TrackerAnalyserBuilder withAssignmentList(List<Assignment> assignmentList) {
        this.assignments = assignmentList;
        return this;
    }

    public TrackerAnalyserBuilder withFinder() {
        this.finder = new Finder();
        return this;
    }

    public TrackerAnalyserBuilder withSearchContext() {
        this.searchContext = new CourseGroupSearchContext();
        return this;
    }

    public TrackerAnalyserBuilder withStrengthSearchContext() {
        this.strengthSearchContext = new CourseStrengthSearchContext();
        return this;
    }

    public TrackerStatistics build() {
        return new TrackerStatistics(studentMap, courseSubmissions,
                assignments, finder, searchContext, strengthSearchContext);
    }
}
