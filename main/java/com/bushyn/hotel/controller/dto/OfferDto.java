package com.bushyn.hotel.controller.dto;

import com.bushyn.hotel.controller.dto.group.OnCreate;
import com.bushyn.hotel.controller.dto.group.OnUpdate;
import com.bushyn.hotel.model.enums.OfferStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferDto {
    @Null(message = "id must be empty on creation", groups = OnCreate.class)
    private Long id;

    @NotBlank(message = "user id can`t be empty", groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    private Long userId;

    @NotBlank(message = "roomId can`t be empty", groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    private Long roomId;

    @NotBlank(message = "orderId can`t be empty", groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    private Long orderId;

    @NotBlank(message = "offerStatus can`t be empty")
    private OfferStatus offerStatus;
}
