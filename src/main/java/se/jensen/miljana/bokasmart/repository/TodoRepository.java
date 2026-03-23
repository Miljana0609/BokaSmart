package se.jensen.miljana.bokasmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.miljana.bokasmart.model.Todo;
import se.jensen.miljana.bokasmart.model.User;

import java.util.List;

/**
 * Repository interface for Todo entities.
 * Handles database operations for todo items,
 * including retrieval by user.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
//    List<Todo> findById(Long id);
}
