package se.jensen.miljana.bokasmart.dto;

import se.jensen.miljana.bokasmart.model.Todo;

/**
 * DTO used for returning todo data to clients.
 * Represents a simplified view of a todo item.
 */

public class TodoResponseDto {
    private Long id;
    private String title;
    private String text;
    private boolean completed;


    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.text = todo.getText();
        this.completed = todo.isCompleted();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
