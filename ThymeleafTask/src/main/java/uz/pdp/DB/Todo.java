package uz.pdp.DB;

import java.time.LocalDateTime;

public class Todo {
    private static int count = 0;
    private Integer id;
    private String title;
    private String priority;
    private LocalDateTime createdAt;

    public Todo() {
        this.id = ++count;
        this.createdAt = LocalDateTime.now();
    }

    // Constructors
    public Todo(String title, String priority) {
        this();
        this.title = title;
        this.priority = priority;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
