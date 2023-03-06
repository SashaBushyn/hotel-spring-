package com.bushyn.hotel;

import com.bushyn.hotel.OrderUtil;
import com.bushyn.hotel.RoomUtil;
import com.bushyn.hotel.UserUtil;
import com.bushyn.hotel.controller.dto.OfferDto;
import com.bushyn.hotel.model.entity.Booking;
import com.bushyn.hotel.model.entity.Offer;
import com.bushyn.hotel.model.enums.OfferStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OfferUtil {
    public static Offer testOffer(int i){
        return Offer.builder()
                .offerStatus(OfferStatus.INPROCESS)
                .id(0L +1)
                .order(OrderUtil.orderTest(i))
                .room(RoomUtil.roomTest(i))
                .user(UserUtil.testUser(i))
                .build();
    }
    public static OfferDto testOfferDto(int i){
        return OfferDto.builder()
                .offerStatus(OfferStatus.INPROCESS)
                .id(0L +1)
                .orderId((long) i)
                .roomId((long)i)
                .userId((long)i)
                .build();
    }
    public static Booking testBookingFromOffer(int i){
        Offer offer = testOffer(i);
        return Booking.builder()
                .dateCreation(LocalDateTime.now())
                .dateIn(offer.getOrder().getDateIn())
                .dateOut(offer.getOrder().getDateOut())
                .room(offer.getRoom())
                .user(offer.getUser())
                .id((long)i)
                .sum(Double.valueOf(100 * i))
                .build();

    }
}
