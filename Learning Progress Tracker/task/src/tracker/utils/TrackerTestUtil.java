package tracker.utils;

import tracker.builder.StudentBuilder;
import tracker.builder.TrackerAnalyserBuilder;
import tracker.model.Assignment;
import tracker.model.Course;
import tracker.model.Student;
import tracker.service.statistics.Statistic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

import static tracker.utils.TrackerHelperFunction.initialSubmission;
import static tracker.utils.TrackerUtil.BASE_COURSES;

public class TrackerTestUtil {

    /**
     * This method is only for test purpose
     *
     * @param submissionData Test data
     * @return Statistic class object
     */
    public static Statistic init(List<List<Long>> submissionData) {
        Map<Long, Student> studentMap = new ConcurrentHashMap<>();
        Map<String, Long> initialSubmission = initialSubmission();
        List<Assignment> assignments = new ArrayList<>();

        submissionData.forEach(list -> {
            long id = list.get(0);
            Student student;
            if (studentMap.containsKey(id)) {
                student = studentMap.get(id);
            } else {
                student = StudentBuilder.init()
                        .withId(id)
                        .withCourses()
                        .build();
            }

            IntStream.range(1, list.size())
                    .forEach(i -> {
                        String name = BASE_COURSES.get(i - 1);
                        Long points = list.get(i);
                        student.updateCourse(name, points);
                        if (points > 0) {
                            long count = initialSubmission.get(name) + 1;
                            initialSubmission.put(name, count);
                            Assignment assignment = new Assignment(id, new Course(name, points));
                            assignments.add(assignment);
                        }
                    });

            studentMap.put(id, student);
        });

        return TrackerAnalyserBuilder.init()
                .withStudentMap(studentMap)
                .withCourseSubmissions(initialSubmission)
                .withAssignmentList(assignments)
                .withFinder()
                .withSearchContext()
                .withStrengthSearchContext()
                .build();
    }
}
