package com.revature.watercanapporderms.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.watercanapporderms.dto.OrderDTO;
import com.revature.watercanapporderms.dto.StockDTO;
import com.revature.watercanapporderms.exception.ServiceException;
import com.revature.watercanapporderms.model.Order;
import com.revature.watercanapporderms.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	Order order = new Order();
	
   @Autowired
	StockService stockService;

	@Transactional
	public Order orderStock(OrderDTO orderDto) throws ServiceException {
		Order result = null;        
		
		 List<StockDTO> stockList=stockService.findAllStocks();
		 StockDTO stockInDB = stockList.get(0);
		 int cansAvail=stockInDB.getAvailableCans();
		if (order.getOrderCans() <= cansAvail) {
			order.setOrderCans(orderDto.getOrderCans());
			order.setUserId(order.getUserId());
			order.setUserName(orderDto.getUserName());
			order.setDate(LocalDateTime.now());
			result = orderRepository.save(order);
			stockService.addStocks(orderDto);
		} else {
			throw new ServiceException("Invalid cans...please check available stock and re enter the value");
		}
		return result;
	}
}
