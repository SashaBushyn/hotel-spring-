package com.bushyn.hotel.service;

import com.bushyn.hotel.controller.dto.OrderDto;
import com.bushyn.hotel.model.enums.OrderHandling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);

    Page<OrderDto> getAllOrders(Pageable pageable);

    Page<OrderDto> getUserOrders(Long userId, Pageable pageable);

    OrderDto getOrderById(Long id);

    void deleteOrder(Long id);

    OrderDto changeStatus(Long id, OrderHandling handling);

}
