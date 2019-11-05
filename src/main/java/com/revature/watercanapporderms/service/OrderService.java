package com.revature.watercanapporderms.service;

import java.util.List;
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

	public Order orderStock(OrderDTO orderDto) throws ServiceException {
		Order result = null;  
		Order order = new Order();
		order.setOrderCans(orderDto.getOrderCans());
		order.setUserId(orderDto.getUserId());
		 List<StockDTO> stockList=stockService.findAllStocks();
		 StockDTO stockInDB = stockList.get(0);
		 int cansAvail=stockInDB.getAvailableCans();
		if (order.getOrderCans() <= cansAvail) {
			try {
				order.setUserName(orderDto.getUserName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				result = orderRepository.save(order);
				stockService.addStocks(orderDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new ServiceException("Invalid cans...please check available stock and re enter the value");
		}
		return result;
	}
	
	  public List<Order> viewOrders() throws ServiceException {
	       List<Order> list = null;
	       list = orderRepository.findAll();
	       if (list == null) {
	           throw new ServiceException("Unable to view orderlist");
	       }
	       return list;
	}

}