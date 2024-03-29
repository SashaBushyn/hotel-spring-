package com.bushyn.hotel.service.impl;

import com.bushyn.hotel.controller.dto.OrderDto;
import com.bushyn.hotel.mappers.OrderMapper;
import com.bushyn.hotel.model.entity.Order;
import com.bushyn.hotel.model.entity.User;
import com.bushyn.hotel.model.enums.OrderHandling;
import com.bushyn.hotel.model.exception.EntityException;
import com.bushyn.hotel.repository.OrderRepository;
import com.bushyn.hotel.repository.UserRepository;
import com.bushyn.hotel.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("create order by userId {}", orderDto.getId());
        Order order = orderMapper.orderDtoToOrder(orderDto);
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new EntityException("user with id: " + orderDto.getUserId() + " is not found"));
        order.setUser(user);
        order = orderRepository.save(order);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public Page<OrderDto> getAllOrders(Pageable pageable) {
        log.info("get all orders");
        return new PageImpl<>(orderRepository.findAll(pageable)
                .stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList()));
    }

    @Override
    public Page<OrderDto> getUserOrders(Long userId, Pageable pageable) {
        log.info("get userid {}  orders", userId);
        return new PageImpl<>(orderRepository.findOrdersByUserId(userId)
                .stream()
                .map(orderMapper::orderToOrderDto).collect(Collectors.toList()));
    }

    @Override
    public OrderDto getOrderById(Long id) {
        log.info("get order by id {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityException("order with id: " + id + " is not found"));
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public void deleteOrder(Long id) {
        log.info("delete order by id {}", id);
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDto changeStatus(Long id, OrderHandling handling) {
        log.info("change status on {} in order id {}", handling, id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityException("order with id: " + id + " is not found"));
        order.setStatus(handling);
        order = orderRepository.save(order);
        return orderMapper.orderToOrderDto(order);
    }
}
