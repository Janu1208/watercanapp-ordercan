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
	
	
   @Autowired
	StockService stockService;

	@Transactional
	public Order orderStock(OrderDTO orderDto) throws ServiceException {
		Order result = null;  
		Order order = new Order();
		order.setOrderCans(orderDto.getOrderCans());
		order.setUserId(orderDto.getUserId());
		 List<StockDTO> stockList=stockService.findAllStocks();
		 StockDTO stockInDB = stockList.get(0);
		 int cansAvail=stockInDB.getAvailableCans();
		if (order.getOrderCans() <= cansAvail) {
			System.out.println("Service If statement Called");
			order.setUserName(orderDto.getUserName());
			System.out.println("Before  Order Save:" +  order);
			result = orderRepository.save(order);
			System.out.println("AfterSave:"  + result);
			stockService.addStocks(orderDto);
		} else {
			throw new ServiceException("Invalid cans...please check available stock and re enter the value");
		}
		return result;
	}
}