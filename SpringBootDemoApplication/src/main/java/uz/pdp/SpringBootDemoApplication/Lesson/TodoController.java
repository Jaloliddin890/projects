package uz.pdp.SpringBootDemoApplication.Lesson;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.SpringBootDemoApplication.Exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TodoController {

    AtomicInteger counter = new AtomicInteger(0);

    List<Todo> todos = new ArrayList<>(){{
        add(new Todo(counter.incrementAndGet(),"Spring Boot","HIGH"));
        add(new Todo(counter.incrementAndGet(),"Spring MVC","MEDIUM"));
    }};

    @GetMapping(value = "/todos", consumes = "application/json", produces = "application/xml")
    public ResponseEntity<List<Todo>> getAll(){
        return ResponseEntity.ok(todos);
    }

    @GetMapping(value = "/todos/{id}")
    public ResponseEntity<Todo> get(@PathVariable Integer id){
        return ResponseEntity.ok(todos.stream().filter(t->t.getId().equals(id))
                .findFirst()
                .orElseThrow(()->(new NotFoundException("No such id: "+id))));
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@Valid @RequestBody Todo todo){
        todo.setId(counter.incrementAndGet());
        todos.add(todo);
        return todo;
    }




}


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Todo {
    private Integer id;

    @NotBlank
    private String title;
    @NotBlank
    private String priority;
}
