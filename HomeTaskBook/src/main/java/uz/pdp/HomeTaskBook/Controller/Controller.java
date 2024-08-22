package uz.pdp.HomeTaskBook.Controller;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.HomeTaskBook.Book.Book;
import uz.pdp.HomeTaskBook.BookDTO.dto;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    private JdbcTemplate jdbcTemplate;

    public Controller(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @GetMapping("/")
    public String home(Model model) {
        var sql = "SELECT * FROM book";
        List<Book> books = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Book.class));
        model.addAttribute("books", books);
        return "home";
    }

    @GetMapping("/create")
    public String create() {
        return "createPage";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute dto dto) {
        var sql = "INSERT INTO book (title, description, price, author) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, dto.getTitle(), dto.getDescription(), dto.getPrice(), dto.getAuthor());
        return "redirect:/";
    }


    @GetMapping("/search")
    public String bookSearch(Model model, @RequestParam(required = false) String search) {

        String sql = "select * from book where title ilike '%" + search + "%' or author ilike '%" + search + "%' or description ilike '%" + search + "%'";
        List<Book> books = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Book.class));
        model.addAttribute("books", books);
        return "searchBook";
    }

    @GetMapping("/update/{id}")
    public String bookUpdatePage(@PathVariable int id, Model model) {
        Book book = jdbcTemplate.queryForObject("select * from book where id=?", BeanPropertyRowMapper.newInstance(Book.class), id);
        model.addAttribute("book", book);
        return "bookUpdate";
    }

    @PostMapping("/update")
    public String bookUpdate(@ModelAttribute("book") Book book) {
        var sql = "update book set title = ?, author = ?, price = ?, description = ? where id = ?";
        jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getDescription(),
                book.getId()
        );
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String bookDelete(@PathVariable int id, Model model) {
        Book book = jdbcTemplate.queryForObject("select * from book where id=?", BeanPropertyRowMapper.newInstance(Book.class), id);
        model.addAttribute("book", book);
        return "deleteBook";
    }

    @PostMapping("/delete")
    public String bookDelete(@RequestParam("id") int id) {
        jdbcTemplate.update("delete from book where id=?", id);
        return "redirect:/";
    }

}
