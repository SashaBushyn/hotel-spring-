package com.bushyn.hotel.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservedRooms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dateOfReserve;
    @ManyToOne(cascade = CascadeType.ALL)
    private Booking booking;
    @ManyToOne(cascade = CascadeType.ALL)
    private Room room;
}
