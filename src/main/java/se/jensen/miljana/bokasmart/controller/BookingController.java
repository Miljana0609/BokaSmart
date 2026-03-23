package se.jensen.miljana.bokasmart.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import se.jensen.miljana.bokasmart.dto.BookingRequestDto;
import se.jensen.miljana.bokasmart.dto.BookingResponseDto;
import se.jensen.miljana.bokasmart.model.Booking;
import se.jensen.miljana.bokasmart.service.AuthService;
import se.jensen.miljana.bokasmart.service.BookingService;

import java.util.List;

/**
 * REST controller for managing bookings.
 * <p>
 * Provides endpoints to create, retrieve, and delete bookings
 * for the authenticated user.
 */

@RestController
@RequestMapping("/api/bookings")
@SecurityRequirement(name = "bearerAuth")
public class BookingController {

    private final BookingService bookingService;
    private final AuthService authService;


    public BookingController(BookingService bookingService, AuthService authService) {
        this.bookingService = bookingService;
        this.authService = authService;
    }

    // === Skapa bokning ===
    @PostMapping("/create")
    public ResponseEntity<BookingResponseDto> createBooking(
            @Valid @RequestBody BookingRequestDto requestDto,
            Authentication authentication) {

        String username = authentication.getName();

        Booking booking = bookingService.createBooking(
                username,
                requestDto
        );

        return ResponseEntity.ok(new BookingResponseDto(booking));
    }

    // === Hämta alla bokningar ===
    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getBookings(Authentication authentication) {

        List<BookingResponseDto> bookings = bookingService
                .getUserBookings(authentication.getName())
                .stream()
                .map(BookingResponseDto::new)
                .toList();

        return ResponseEntity.ok(bookings);
    }

    // === Ta bort bokningar ===
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId,
                                              Authentication authentication) {

        bookingService.deleteBooking(bookingId, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
