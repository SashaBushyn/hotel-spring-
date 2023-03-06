package com.bushyn.hotel.repository;

import com.bushyn.hotel.model.entity.Order;
import com.bushyn.hotel.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUserId(Long userid);
}
