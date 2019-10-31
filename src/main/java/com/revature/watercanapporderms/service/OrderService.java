package com.revature.watercanapporderms.service;

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
		order.setOrderCans(orderDto.getOrderCans());
		order.setUserId(order.getUserId());
		 List<StockDTO> stockList=stockService.findAllStocks();
		 StockDTO stockInDB = stockList.get(0);
		 int cansAvail=stockInDB.getAvailableCans();
		 System.out.println(cansAvail);
		 System.out.println(order.getOrderCans());
		if (order.getOrderCans() <= cansAvail) {
			order.setUserName(orderDto.getUserName());
			result = orderRepository.save(order);
			stockService.addStocks(orderDto);
		} else {
			throw new ServiceException("Invalid cans...please check available stock and re enter the value");
		}
		return result;
	}
}
