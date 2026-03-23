package se.jensen.miljana.bokasmart.dto;

import se.jensen.miljana.bokasmart.model.Booking;
import se.jensen.miljana.bokasmart.model.CalendarType;

import java.time.LocalDateTime;

/**
 * DTO used for returning booking data to clients.
 * Represents a simplified view of booking information.
 */

public class BookingResponseDto {
    private Long id;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private CalendarType calendarType;


    public BookingResponseDto(Long id, String title,
                              LocalDateTime startTime, LocalDateTime endTime,
                              CalendarType calendarType) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.calendarType = calendarType;
    }

    public BookingResponseDto(Booking booking) {
        this.id = booking.getId();
        this.title = booking.getTitle();
        this.startTime = booking.getStartTime();
        this.endTime = booking.getEndTime();
        this.calendarType = booking.getCalendarType();
    }

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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public CalendarType getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(CalendarType calendarType) {
        this.calendarType = calendarType;
    }
}
