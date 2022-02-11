package task;

import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;

@RestController
@Validated
public class Controller {

    @GetMapping("/test/{id}")
    public int test(@PathVariable @Min(1) @Max(99) int id) {
        return id;
    }
}
