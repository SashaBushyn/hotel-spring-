package com.bushyn.hotel.controller.dto;

import com.bushyn.hotel.controller.dto.group.OnCreate;
import com.bushyn.hotel.controller.dto.group.OnUpdate;
import com.bushyn.hotel.controller.validation.DateConstraint;
import com.bushyn.hotel.model.entity.User;
import com.bushyn.hotel.model.enums.OrderHandling;
import com.bushyn.hotel.model.enums.RoomClass;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    @Null(message = "id must be empty on creation", groups = OnCreate.class)
    private Long id;

    @NotNull(message = "user id can`t be empty", groups = OnCreate.class)
    @Null(message = "user id must be empty on creation", groups = OnUpdate.class)
    private Long userId;

    @NotBlank(message = "date in can`t be empty", groups = OnCreate.class)
    @Null(message = "dateIn must be empty on creation", groups = OnUpdate.class)
    @DateConstraint
    private LocalDate dateIn;

    @NotBlank(message = "date out  can`t be empty", groups = OnCreate.class)
    @Null(message = "dateOut must be empty on creation", groups = OnUpdate.class)
    private LocalDate dateOut;

    @NotBlank(message = "room class can`t be empty", groups = OnCreate.class)
    @Null(message = "roomClass must be empty on creation", groups = OnUpdate.class)
    private RoomClass roomClass;

    @NotBlank(message = "persons number can`t be empty", groups = OnCreate.class)
    @Null(message = "personsNumber must be empty on creation", groups = OnUpdate.class)
    private Integer personsNumber;

    @NotBlank(message = "status can`t be empty", groups = OnUpdate.class)
    private OrderHandling status;
}
