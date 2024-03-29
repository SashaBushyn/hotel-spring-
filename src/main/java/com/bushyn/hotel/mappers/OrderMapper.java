package com.bushyn.hotel.mappers;

import com.bushyn.hotel.controller.dto.OrderDto;
import com.bushyn.hotel.model.entity.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "userId", source = "order.user.id")
    OrderDto orderToOrderDto(Order order);
    @Mapping(target = "user", ignore = true)
    Order orderDtoToOrder(OrderDto orderDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "user", ignore = true)
    Order updateOrder(@MappingTarget Order order, OrderDto orderDto);
}
