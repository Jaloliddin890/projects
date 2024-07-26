package uz.pdp.Service;

import org.springframework.stereotype.Service;
import uz.pdp.DB.Todo;
import uz.pdp.DB.TodoDAO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



@Service
public class TodoList {

    private final TodoDAO todoDAO;

    public TodoList(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    public List<Todo> getAllTodos() {
        return todoDAO.getAllTodos();
    }

    public void addTodo(Todo todo) {
        todoDAO.addTodo(todo);
    }

    public void updateTodo(Todo todo) {
        todoDAO.updateTodo(todo);
    }

    public void deleteTodo(int id) {
        todoDAO.deleteTodo(id);
    }
}
