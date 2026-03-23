package se.jensen.miljana.bokasmart.service;


import org.springframework.stereotype.Service;
import se.jensen.miljana.bokasmart.dto.TodoRequestDto;
import se.jensen.miljana.bokasmart.dto.TodoResponseDto;
import se.jensen.miljana.bokasmart.model.Todo;
import se.jensen.miljana.bokasmart.model.User;
import se.jensen.miljana.bokasmart.repository.TodoRepository;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service responsible for todo management.
 * Handles creation, retrieval, updating, and deletion
 * of todo items for users.
 */

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final AuthService authService;

    public TodoService(TodoRepository todoRepository, AuthService authService) {
        this.todoRepository = todoRepository;
        this.authService = authService;
    }

    //=== Skapa ===
    public TodoResponseDto createTodo(TodoRequestDto requestDto, String username) {

        User user = authService.getByUsername(username);

        Todo todo = new Todo();
        todo.setTitle(requestDto.getTitle());
        todo.setText(requestDto.getText());
        todo.setUser(user);
        todo.setCompleted(false);

        return new TodoResponseDto(todoRepository.save(todo));
    }

    // === Hämta alla todos för en user ===
    public List<TodoResponseDto> getUserTodos(String username) {
        User user = authService.getByUsername(username);
        return todoRepository.findByUser(user)
                .stream()
                .map(TodoResponseDto::new)
                .toList();
    }

    // === Toggle completed ===
    public TodoResponseDto toggleCompleted(Long todoId, String username) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchElementException("Todo-List not found!"));

        if (!todo.getUser().getUsername().equals(username)) {
            throw new SecurityException("Not your todo-list");
        }
        todo.setCompleted(!todo.isCompleted());
        return new TodoResponseDto(todoRepository.save(todo));
    }

    public void deleteTodo(Long todoId, String username) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchElementException("Todo-List not found!"));

        if (!todo.getUser().getUsername().equals(username)) {
            throw new SecurityException("Not your todo-list!");
        }
        todoRepository.delete(todo);
    }
}
