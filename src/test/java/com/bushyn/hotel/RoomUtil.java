package com.bushyn.hotel;

import com.bushyn.hotel.controller.dto.RoomDto;
import com.bushyn.hotel.model.entity.Room;
import com.bushyn.hotel.model.enums.RoomClass;

public class RoomUtil {
    public static Room roomTest(int i) {
        return Room.builder()
                .roomNumber(i)
                .id(1L +i)
                .personsNumber(i)
                .price(Double.valueOf(100 * i))
                .blocked(false)
                .roomClass(RoomClass.ECONOM)
                .build();
    }

    public static RoomDto roomDtoTest(int i) {
        return RoomDto.builder()
                .roomNumber(i)
                .id(1L +i)
                .personsNumber(i)
                .price(Double.valueOf(100 * i))
                .blocked(false)
                .roomClass(RoomClass.ECONOM)
                .build();
    }
}
