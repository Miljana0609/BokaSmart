package se.jensen.miljana.bokasmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.miljana.bokasmart.model.Booking;
import se.jensen.miljana.bokasmart.model.CalendarType;
import se.jensen.miljana.bokasmart.model.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for Booking entities.
 * Provides database operations for bookings,
 * including custom queries for user-specific data.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);

    List<Booking> findByUserAndCalendarType(User user, CalendarType calendarType);

    boolean existsByUserAndStartTime(LocalDateTime start, LocalDateTime end, User user);

}
