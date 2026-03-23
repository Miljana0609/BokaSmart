package se.jensen.miljana.bokasmart.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jensen.miljana.bokasmart.dto.TodoRequestDto;
import se.jensen.miljana.bokasmart.dto.TodoResponseDto;
import se.jensen.miljana.bokasmart.model.Todo;
import se.jensen.miljana.bokasmart.model.User;
import se.jensen.miljana.bokasmart.repository.TodoRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for TodoService.
 * Tests core functionality such as creating todos,
 * retrieving user todos, updating completion status,
 * and enforcing security rules.
 * Uses Mockito to simulate repository and service dependencies.
 */

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private TodoService todoService;


    // === CREATE ===
    @Test
    void shouldCreateTodo() {
        String username = "Ellen";

        User user = new User();
        user.setUsername(username);

        TodoRequestDto dto = new TodoRequestDto();
        dto.setTitle("Test todo");
        dto.setText("Test text");

        when(authService.getByUsername(username)).thenReturn(user);
        when(todoRepository.save(any(Todo.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TodoResponseDto result = todoService.createTodo(dto, username);

        assertNotNull(result);
        assertEquals("Test todo", result.getTitle());
        verify(todoRepository).save(any(Todo.class));
    }

    // === GET TODOS ===
    @Test
    void shouldReturnUserTodos() {
        String username = "Ellen";

        User user = new User();
        user.setUsername(username);

        Todo todo = new Todo();
        todo.setTitle("Test");

        when(authService.getByUsername(username)).thenReturn(user);
        when(todoRepository.findByUser(user)).thenReturn(List.of(todo));

        List<TodoResponseDto> result = todoService.getUserTodos(username);

        assertEquals(1, result.size());
        verify(todoRepository).findByUser(user);
    }

    // === TOGGLE COMPLETED ===
    @Test
    void shouldToggleTodoCompleted() {
        String username = "Ellen";

        Todo todo = new Todo();
        todo.setCompleted(false);

        User user = new User();
        user.setUsername(username);
        todo.setUser(user);

        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        when(todoRepository.save(any(Todo.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TodoResponseDto result = todoService.toggleCompleted(1L, username);

        assertTrue(result.isCompleted());
    }

    // === DELETE SUCCESS ===
    @Test
    void shouldDeleteTodo_whenUserOwnsTodo() {
        String username = "Ellen";

        User user = new User();
        user.setUsername(username);

        Todo todo = new Todo();
        todo.setUser(user);

        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        todoService.deleteTodo(1L, username);

        verify(todoRepository).delete(todo);
    }

    // === DELETE FAIL (SECURITY) ===
    @Test
    void shouldThrowException_whenDeletingOthersTodo() {
        Todo todo = new Todo();

        User owner = new User();
        owner.setUsername("Other");

        todo.setUser(owner);

        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        assertThrows(SecurityException.class, () ->
                todoService.deleteTodo(1L, "Ellen")
        );
    }

    // === NOT FOUND ===
    @Test
    void shouldThrowException_whenTodoNotFound() {
        when(todoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
                todoService.toggleCompleted(99L, "Ellen")
        );
    }
}
