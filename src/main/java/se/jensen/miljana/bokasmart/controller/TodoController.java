package se.jensen.miljana.bokasmart.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import se.jensen.miljana.bokasmart.dto.TodoRequestDto;
import se.jensen.miljana.bokasmart.dto.TodoResponseDto;
import se.jensen.miljana.bokasmart.service.TodoService;

import java.util.List;

/**
 * REST controller for managing todo items.
 * Allows authenticated users to create, view, update,
 * and delete their personal todo tasks.
 */

@RestController
@RequestMapping("/api/todos")
@SecurityRequirement(name = "bearerAuth")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // === Skapa ===
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(
            @Valid @RequestBody TodoRequestDto dto,
            Authentication authentication) {

        return ResponseEntity.ok(
                todoService.createTodo(dto, authentication.getName())
        );
    }

    // === Hämta alla ===
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodos(Authentication authentication) {

        return ResponseEntity.ok(
                todoService.getUserTodos(authentication.getName())
        );
    }

    // === Toggle completed ===
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> toggleTodo(
            @PathVariable Long todoId,
            Authentication authentication) {

        return ResponseEntity.ok(
                todoService.toggleCompleted(todoId, authentication.getName())
        );
    }

    // === Delete ===
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long todoId,
            Authentication authentication) {

        todoService.deleteTodo(todoId, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}