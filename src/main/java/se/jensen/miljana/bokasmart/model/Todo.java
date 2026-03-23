package se.jensen.miljana.bokasmart.model;


import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entity representing a todo item.
 * Stores task details such as title, description,
 * completion status, and associated user.
 */

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    private boolean completed = false;

    private LocalDate createdDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // === GETTERS/SETTERS ===


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
