package com.bushyn.hotel.service.impl;

import com.bushyn.hotel.controller.dto.RoomDto;
import com.bushyn.hotel.mappers.RoomMapper;
import com.bushyn.hotel.model.entity.Room;
import com.bushyn.hotel.model.exception.EntityException;
import com.bushyn.hotel.repository.RoomRepository;
import com.bushyn.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public RoomDto CreateRoom(RoomDto roomDto) {
        log.info("createRoom with number {}", roomDto.getRoomNumber());
        Room room = roomMapper.roomDtoToRoom(roomDto);
        room = roomRepository.save(room);
        return roomMapper.roomToRoomDto(room);
    }

    @Override
    public RoomDto GetRoom(Long id) {
        log.info("get room by id {}", id);
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityException("room with id: " + id + " is not found"));
        return roomMapper.roomToRoomDto(room);
    }

    @Override
    public Page<RoomDto> GetAllRooms(Pageable pageable) {
        log.info("get all rooms");
        return new PageImpl<>(roomRepository.findAll(pageable)
                .stream()
                .map(roomMapper::roomToRoomDto)
                .collect(Collectors.toList()));
    }

    @Override
    public RoomDto updateRoom(Long id, RoomDto roomDto) {
        log.info("update room id = {}", id);
        Room persistedRoom = roomRepository.getById(id);
        persistedRoom = roomMapper.updateRoom(persistedRoom, roomDto);
        roomRepository.save(persistedRoom);
        return roomMapper.roomToRoomDto(persistedRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) throw new EntityException("room with id " + id + "is not found");
        log.info("delete room with id {}", id);
        roomRepository.deleteById(id);
    }

    @Override
    public Page<RoomDto> getFreeRoomsOnDates(LocalDate dateIn, LocalDate dateOut, Pageable pageable) {
        log.info("get free rooms on dates {}, {}", dateIn, dateOut);
        return roomRepository.getFreeRoomsOnDates(dateIn, dateOut, pageable).map(room  -> roomMapper.roomToRoomDto(room));
    }

    @Override
    public List<RoomDto> getFreeRoomsForOrder(Long id) {
        return roomRepository.getFreeRoomsForOrder(id).stream()
                .map(room -> roomMapper.roomToRoomDto(room))
                .collect(Collectors.toList());
    }
}
