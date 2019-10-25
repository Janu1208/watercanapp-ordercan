package com.revature.watercanapporderms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.watercanapporderms.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
