package com.booking.app.service;

import java.util.List;

import com.booking.app.dto.BookingRequest;
import com.booking.app.dto.BookingResponse;
import com.booking.app.entity.BookingStatus;

public interface BookingService {
    BookingResponse createBooking(Long userId, BookingRequest request);
    BookingResponse updateBookingStatus(Long bookingId, BookingStatus status);
    List<BookingResponse> getBookingsForUser(Long userId);
    List<BookingResponse> getAllBookings(); // Admin
    BookingResponse getBookingById(Long id);
    void cancelBooking(Long bookingId, Long userId);
}
