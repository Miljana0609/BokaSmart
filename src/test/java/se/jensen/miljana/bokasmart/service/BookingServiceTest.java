package se.jensen.miljana.bokasmart.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jensen.miljana.bokasmart.dto.BookingRequestDto;
import se.jensen.miljana.bokasmart.model.Booking;
import se.jensen.miljana.bokasmart.model.CalendarType;
import se.jensen.miljana.bokasmart.model.User;
import se.jensen.miljana.bokasmart.repository.BookingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookingService.
 * Verifies business logic related to booking creation,
 * retrieval, deletion, and security rules.
 * Uses Mockito to mock dependencies and isolate service behavior.
 */

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private BookingService bookingService;

    // ===CREATE===

    @Test
    void shouldCreateBooking() {
        // Arrange
        String username = "Ellen";

        User user = new User();
        user.setId(1L);
        user.setUsername(username);

        BookingRequestDto dto = new BookingRequestDto();
        dto.setTitle("Test booking");
        dto.setStartTime(LocalDateTime.now());
        dto.setEndTime(LocalDateTime.now().plusHours(1));
        dto.setCalendarType(CalendarType.WORK);

        when(authService.getByUsername(username)).thenReturn(user);
        when(bookingRepository.save(any(Booking.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Booking result = bookingService.createBooking(username, dto);

        // Assert
        assertNotNull(result);
        assertEquals("Test booking", result.getTitle());
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    // ===GET===
    @Test
    void shouldReturnUserBookings() {
        String username = "Ellen";

        User user = new User();
        user.setUsername(username);

        Booking booking = new Booking();
        booking.setTitle("Test");

        when(authService.getByUsername(username)).thenReturn(user);
        when(bookingRepository.findByUser(user)).thenReturn(List.of(booking));

        List<Booking> result = bookingService.getUserBookings(username);

        assertEquals(1, result.size());
    }

    // ===DELETE===

    @Test
    void shouldDeleteBooking_whenUserOwnsBooking() {
        String username = "Ellen";

        User user = new User();
        user.setId(1L);
        user.setUsername(username);

        Booking booking = new Booking();
        booking.setId(10L);
        booking.setUser(user);

        when(authService.getByUsername(username)).thenReturn(user);
        when(bookingRepository.findById(10L)).thenReturn(Optional.of(booking));

        bookingService.deleteBooking(10L, username);

        verify(bookingRepository).delete(booking);
    }
}
