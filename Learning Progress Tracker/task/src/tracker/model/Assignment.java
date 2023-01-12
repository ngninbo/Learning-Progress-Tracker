package tracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Assignment {

    private Long studentId;
    private Course course;
}
