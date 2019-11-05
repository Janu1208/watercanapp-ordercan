package com.revature.watercanapporderms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.watercanapporderms.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query(" from Order where id = :id")
    Order findByCancelId(@Param("id")int id);

}
