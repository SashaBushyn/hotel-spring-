package com.bushyn.hotel.mappers;

import com.bushyn.hotel.controller.dto.RoomDto;
import com.bushyn.hotel.model.entity.Room;
import com.bushyn.hotel.model.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDto roomToRoomDto(Room room);

    Room roomDtoToRoom(RoomDto roomDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Room updateRoom(@MappingTarget Room room, RoomDto roomDto);
}
