package se.jensen.miljana.bokasmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.miljana.bokasmart.model.User;

import java.util.Optional;

/**
 * Repository interface for User entities.
 * Provides database access and lookup functionality
 * for users by username.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
