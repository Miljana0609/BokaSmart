package se.jensen.miljana.bokasmart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import se.jensen.miljana.bokasmart.model.CalendarType;

import java.time.LocalDateTime;

/**
 * DTO used when creating a new booking.
 * Contains input data required to create a booking.
 */

public class BookingRequestDto {


    @NotBlank
    private String title;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    @NotNull
    private CalendarType calendarType;

    public BookingRequestDto() {
    }

    public BookingRequestDto(String title,
                             LocalDateTime startTime, LocalDateTime endTime,
                             CalendarType calendarType) {

        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.calendarType = calendarType;
    }

    public String getTitle() {
        return title;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }


    public LocalDateTime getEndTime() {
        return endTime;
    }

    public CalendarType getCalendarType() {
        return calendarType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setCalendarType(CalendarType calendarType) {
        this.calendarType = calendarType;
    }
}
