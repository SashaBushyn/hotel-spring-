package com.bushyn.hotel.service.impl;

import com.bushyn.hotel.BookingUtil;
import com.bushyn.hotel.RoomUtil;
import com.bushyn.hotel.UserUtil;
import com.bushyn.hotel.controller.dto.BookingDto;
import com.bushyn.hotel.mappers.BookingMapper;
import com.bushyn.hotel.model.entity.Booking;
import com.bushyn.hotel.model.entity.ReservedRooms;
import com.bushyn.hotel.model.exception.EntityException;
import com.bushyn.hotel.repository.BookingRepository;
import com.bushyn.hotel.repository.ReservedRoomsRepository;
import com.bushyn.hotel.repository.RoomRepository;
import com.bushyn.hotel.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {
    @Mock
    BookingRepository bookingRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    RoomRepository roomRepository;
    @Mock
    ReservedRoomsRepository reservedRoomsRepository;
    @Spy
    BookingMapper bookingMapper = Mappers.getMapper(BookingMapper.class);
    @InjectMocks
    BookingServiceImpl bookingService;

    @Test
    void createBookingUserNotFoundTest() {
        BookingDto bookingDto = BookingUtil.testBookingDto(1);
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityException.class, () -> bookingService.createBooking(bookingDto));
    }

    @Test
    void createBookingCreatingReservedRoomRecordsTest() {
        BookingDto bookingDto = BookingUtil.testBookingDto(1);
        Booking booking = BookingUtil.testBooking(1);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(UserUtil.testUser(1)));
        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(RoomUtil.roomTest(1)));
        when(bookingRepository.save(any())).thenReturn(booking);
        bookingDto = bookingService.createBooking(bookingDto);
        verify(reservedRoomsRepository, times(2)).save(any(ReservedRooms.class));
    }

    @Test
    void creatingBookingTest() {
        BookingDto bookingDto = BookingUtil.testBookingDto(1);
        Booking booking = BookingUtil.testBooking(1);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(UserUtil.testUser(1)));
        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(RoomUtil.roomTest(1)));
        when(bookingRepository.save(any())).thenReturn(booking);
        bookingDto = bookingService.createBooking(bookingDto);

        assertThat(bookingDto, allOf(
                hasProperty("id", equalTo(booking.getId())),
                hasProperty("userId", equalTo(booking.getUser().getId())),
                hasProperty("roomId", equalTo(booking.getRoom().getId())),
                hasProperty("dateIn", equalTo(booking.getDateIn())),
                hasProperty("dateOut", equalTo(booking.getDateOut()))));
    }


    @Test
    void getAllBookingsTest() {
        Page<Booking> bookingPage = new PageImpl<>(Arrays.asList(mock(Booking.class), mock(Booking.class), mock(Booking.class)));
        Pageable pageable = mock(Pageable.class);
        when(bookingRepository.findAll(pageable)).thenReturn(bookingPage);
        Page<BookingDto> bookingDtoPage = bookingService.getAllBookings(pageable);
        assertEquals(bookingDtoPage.getTotalElements(), bookingPage.getTotalElements());
    }

    @Test
    void getUserBookingsTest() {
        List<Booking> bookingList = Arrays.asList(mock(Booking.class), mock(Booking.class), mock(Booking.class));
        Pageable pageable = mock(Pageable.class);
        when(bookingRepository.findAllByUserId(1L, pageable)).thenReturn(bookingList);

        Page<BookingDto> bookingDtoPage = bookingService.getUserBookings(1l, pageable);

        assertEquals(bookingDtoPage.getTotalElements(), bookingList.size());
    }

    @Test
    void deleteBooking() {
        bookingService.deleteBooking(anyLong());

        verify(bookingRepository, times(1)).deleteById(any());
    }
}