package com.bushyn.hotel.service;

import com.bushyn.hotel.controller.dto.BookingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {

    BookingDto createBooking(BookingDto bookingDto);

    Page<BookingDto> getAllBookings(Pageable pageable);

    Page<BookingDto> getUserBookings(Long userId, Pageable pageable);

    void deleteBooking(Long id);
}
