package se.jensen.miljana.bokasmart.service;


import org.springframework.stereotype.Service;
import se.jensen.miljana.bokasmart.dto.BookingRequestDto;
import se.jensen.miljana.bokasmart.model.Booking;
import se.jensen.miljana.bokasmart.model.CalendarType;
import se.jensen.miljana.bokasmart.model.User;
import se.jensen.miljana.bokasmart.repository.BookingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service for handling booking business logic.
 * Manages creation, retrieval, validation, and deletion
 * of bookings for users.
 */

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final AuthService authService;


    public BookingService(BookingRepository bookingRepository, AuthService authService) {
        this.bookingRepository = bookingRepository;
        this.authService = authService;
    }

    //=== Skapa bokning ===
    public Booking createBooking(String username, BookingRequestDto dto) {

        User user = authService.getByUsername(username);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setTitle(dto.getTitle());
        booking.setStartTime(dto.getStartTime());
        booking.setEndTime(dto.getEndTime());
        booking.setCalendarType(dto.getCalendarType());

        return bookingRepository.save(booking);
    }

    //=== Hämta alla bokningar för user ===
    public List<Booking> getUserBookings(String username) {
        User user = authService.getByUsername(username);
        return bookingRepository.findByUser(user);
    }

    //=== Hämta bokningar per kalender ===
    public List<Booking> getUserBookingsByType(User user, CalendarType type) {
        return bookingRepository.findByUserAndCalendarType(user, type);
    }

    //=== Ta bort bokning ===
    public void deleteBooking(Long bookingId, String username) {

        User user = authService.getByUsername(username);

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NoSuchElementException("Booking not found!"));

        if (!booking.getUser().getId().equals(user.getId())) {
            throw new SecurityException("Not your booking!");
        }

        bookingRepository.delete(booking);
    }

    public void validateNoConflict(LocalDateTime start, LocalDateTime end, User user) {
        boolean exists = bookingRepository.existsByUserAndStartTime(start, end, user);
        if (exists) {
            throw new RuntimeException("Tiden krockar med en annan bokning!");
        }
    }
}
