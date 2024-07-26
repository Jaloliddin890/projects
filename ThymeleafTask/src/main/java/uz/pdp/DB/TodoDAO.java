package uz.pdp.DB;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class TodoDAO {
    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private SimpleJdbcCall getAllTodosCall;
    private SimpleJdbcCall addTodoCall;
    private SimpleJdbcCall updateTodoCall;
    private SimpleJdbcCall deleteTodoCall;


    public TodoDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.initializeJdbcCalls();
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    }

    private void initializeJdbcCalls() {
        this.getAllTodosCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("get_all_todos")
                .returningResultSet("todos", new TodoRowMapper());

        this.addTodoCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("add_todo");

        this.updateTodoCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("update_todo");

        this.deleteTodoCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("delete_todo");
    }

    public List<Todo> getAllTodos() {
        var sql = "SELECT * FROM todo";
        return jdbcTemplate.query(sql, new TodoRowMapper());
    }

//    public List<Todo> getAllTodos() {
//        Map<String, Object> result = getAllTodosCall.execute(new HashMap<>());
//        return (List<Todo>) result.get("todos");
//    }

    public void addTodo(Todo todo) {
        var sql="Insert into todo (title, priority)values(:title,:priority)";

        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(todo));
    }
//    public void addTodo(Todo todo) {
//        Map<String, Object> inParams = new HashMap<>();
//        inParams.put("todo_title", todo.getTitle());
//        inParams.put("todo_priority", todo.getPriority());
//
//        addTodoCall.execute(inParams);
//    }
//    public void addTodoForSimpleJDBCtemplate(Todo todo) {
//        var source = new BeanPropertySqlParameterSource(todo);
//        simpleJdbcInsert.withTableName("todo")
//                .usingColumns("title","priority")
//                .execute(source);
//
//
//    }

    public void updateTodo(Todo todo) {
        var sql = "update todo set title = ?, priority = ? where id = ?";
        jdbcTemplate.update(sql, todo.getTitle(), todo.getPriority(), todo.getId());
    }

//    public void updateTodo(Todo todo) {
//        Map<String, Object> inParams = new HashMap<>();
//        inParams.put("todo_id", todo.getId());
//        inParams.put("todo_title", todo.getTitle());
//        inParams.put("todo_priority", todo.getPriority());
//
//        updateTodoCall.execute(inParams);
//    }

    public void deleteTodo(int id) {
        var sql = "delete from todo where id = ?";
        jdbcTemplate.update(sql, id);
    }

//    public void deleteTodo(int id) {
//        Map<String, Object> inParams = new HashMap<>();
//        inParams.put("todo_id", id);
//
//        deleteTodoCall.execute(inParams);
//    }

    private static class TodoRowMapper implements RowMapper<Todo> {
        @Override
        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Todo todo = new Todo();
            todo.setId(rs.getInt("id"));
            todo.setTitle(rs.getString("title"));
            todo.setPriority(rs.getString("priority"));
            return todo;
        }
    }

//    private static class TodoRowMapper implements RowMapper<Todo> {
//        @Override
//        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Todo todo = new Todo();
//            todo.setId(rs.getInt("id"));
//            todo.setTitle(rs.getString("title"));
//            todo.setPriority(rs.getString("priority"));
//            return todo;
//        }
//    }
}
