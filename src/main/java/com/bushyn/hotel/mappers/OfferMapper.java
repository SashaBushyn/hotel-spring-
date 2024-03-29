package com.bushyn.hotel.mappers;

import com.bushyn.hotel.controller.dto.OfferDto;
import com.bushyn.hotel.model.entity.Offer;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    @Mappings({@Mapping(target = "userId", source = "offer.user.id"),
            @Mapping(target = "roomId", source = "offer.room.id"),
            @Mapping(target = "orderId", source = "offer.order.id")})
    OfferDto offerToOfferDto(Offer offer);

    @Mappings({@Mapping(target = "user", ignore = true),
            @Mapping(target = "room", ignore = true),
            @Mapping(target = "order", ignore = true)})
    Offer offerDtoToOffer(OfferDto offerDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Offer updateOfferMapper(@MappingTarget Offer offer, OfferDto offerDto);
}
