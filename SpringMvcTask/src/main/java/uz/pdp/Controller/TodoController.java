package uz.pdp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@ResponseBody
public class TodoController {

    public List<Todo> todoList = List.of(
            new Todo("Jaloliddin",20),
            new Todo("Jaloliddin",20)
    );

    @GetMapping("/todos")
    public String todos() {
        StringBuilder builder = new StringBuilder();
        for (Todo todo : todoList)
            builder.append("<h1>").append(todo).append("</h1>");
        return builder.toString();
    }

}
