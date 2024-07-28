//package uz.pdp.Controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import uz.pdp.Service.TodoList;
//import uz.pdp.DB.*;
//import java.util.List;
//
//@Controller
//public class TodoController {
//    private final TodoList todoList;
//
//    public TodoController(TodoList todoList) {
//        this.todoList = todoList;
//    }
//
//    @GetMapping("/")
//    public String showTodoList(Model model) {
//        List<Todo> todos = todoList.getTodos();
//        model.addAttribute("todos", todos);
//        return "displayTodos";
//    }
//
//    @GetMapping("/addTodo")
//    public String addTodoForm(Model model) {
//        model.addAttribute("todo", new Todo());
//        return "addTodo";
//    }
//
//    @PostMapping("/addTodo")
//    public String addTodoSubmit(@ModelAttribute Todo todo) {
//        todoList.add(todo);
//        return "redirect:/";
//    }
//
//    @PostMapping("/deleteTodo/{id}")
//    public String deleteTodo(@PathVariable Integer id) {
//        todoList.remove(id);
//        return "redirect:/";
//    }
//
//    @GetMapping("/editTodo/{id}")
//    public String editTodoForm(@PathVariable Integer id, Model model) {
//        Todo todo = todoList.get(id);
//        model.addAttribute("todo", todo);
//        return "editTodo";
//    }
//
//    @PostMapping("/updateTodo/{id}")
//    public String updateTodo(@PathVariable Integer id, @ModelAttribute Todo updatedTodo) {
//        todoList.update(id, updatedTodo);
//        return "redirect:/";
//    }
//}

package uz.pdp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Confg.configSecurity.CustomUserDetails;
import uz.pdp.DB.Todo;
import uz.pdp.Service.TodoList;
import uz.pdp.daos.AuthPermissionDao;
import uz.pdp.daos.AuthRoleDao;
import uz.pdp.daos.AuthUserDao;
import uz.pdp.domains.AuthRoles;

import java.util.List;

@Controller
public class TodoController {
    private final AuthUserDao authUserDao;
    private final AuthRoleDao authRoleDao;
    private final AuthPermissionDao authPermissionDao;

    private final TodoList todoList;

    @Autowired
    public TodoController(AuthUserDao authUserDao, AuthRoleDao authRoleDao, AuthPermissionDao authPermissionDao, TodoList todoList) {
        this.authUserDao = authUserDao;
        this.authRoleDao = authRoleDao;
        this.authPermissionDao = authPermissionDao;
        this.todoList = todoList;
    }

    @GetMapping("/todos")
    public String displayTodos(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {

        Long id =customUserDetails.getAuthUser().getId();
//        List<Todo> todos = authRoleDao.findByUserID(id);
//        model.addAttribute("todos", todos);
        return "displayTodos";
    }

    @GetMapping("/addTodo")
    public String showAddTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "addTodo";
    }

    @PostMapping("/addTodo")
    public String addTodo(@ModelAttribute Todo todo) {
        todoList.addTodo(todo);
        return "redirect:/todos";
    }


    @GetMapping("/editTodo/{id}")
    public String showEditTodoForm(@PathVariable int id, Model model) {
        Todo todo = todoList.getAllTodos().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        model.addAttribute("todo", todo);
        return "editTodo";
    }

    @PostMapping("/editTodo")
    public String updateTodo(@ModelAttribute Todo todo) {
        todoList.updateTodo(todo);
        return "redirect:/todos";
    }

    @PostMapping("/deleteTodo")
    public String deleteTodo(@RequestParam("id") int id) {
        todoList.deleteTodo(id);
        return "redirect:/todos";
    }


}
