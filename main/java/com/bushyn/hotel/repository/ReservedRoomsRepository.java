package com.bushyn.hotel.repository;

import com.bushyn.hotel.model.entity.ReservedRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedRoomsRepository extends JpaRepository<ReservedRooms, Long> {
}
