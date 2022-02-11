package tracker.builder;

import tracker.model.Assignment;
import tracker.model.Student;
import tracker.statistics.CourseGroupSearchContext;
import tracker.statistics.CourseStrengthSearchContext;
import tracker.statistics.Finder;
import tracker.statistics.TrackerAnalyser;

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

    public TrackerAnalyser build() {
        return new TrackerAnalyser(studentMap, courseSubmissions,
                assignments, finder, searchContext, strengthSearchContext);
    }
}
