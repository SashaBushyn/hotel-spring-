package com.bushyn.hotel;

import com.bushyn.hotel.controller.dto.OrderDto;
import com.bushyn.hotel.model.entity.Order;
import com.bushyn.hotel.model.enums.OrderHandling;
import com.bushyn.hotel.model.enums.RoomClass;

import java.time.LocalDate;

public class OrderUtil {
    public static Order orderTest(int i) {
        return Order.builder()
                .id(0L + i)
                .dateIn(LocalDate.now())
                .dateOut(LocalDate.now().plusDays(i))
                .dateCreation(LocalDate.now())
                .personsNumber(i)
                .roomClass(RoomClass.ECONOM)
                .user(UserUtil.testUser(i))
                .status(OrderHandling.INPROCESS)
                .build();
    }

    public static OrderDto orderDtoTest(int i) {
        return OrderDto.builder()
                .id((long)i)
                .dateIn(LocalDate.now())
                .dateOut(LocalDate.now().plusDays(i))
                .personsNumber(i)
                .roomClass(RoomClass.ECONOM)
                .userId(0L + i)
                .status(OrderHandling.INPROCESS)
                .build();
    }
}
