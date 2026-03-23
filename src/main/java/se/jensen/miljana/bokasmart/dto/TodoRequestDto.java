package se.jensen.miljana.bokasmart.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO used when creating or updating a todo item.
 * Contains input data for todo operations.
 */

public class TodoRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String text;

    public TodoRequestDto() {
    }

    public TodoRequestDto(String text, String title) {
        this.text = text;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }
}
