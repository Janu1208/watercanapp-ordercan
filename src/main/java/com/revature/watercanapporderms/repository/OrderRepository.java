package com.revature.watercanapporderms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.watercanapporderms.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
