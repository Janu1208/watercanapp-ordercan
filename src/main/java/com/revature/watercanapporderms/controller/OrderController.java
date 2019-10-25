package com.revature.watercanapporderms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.watercanapporderms.dto.Message;
import com.revature.watercanapporderms.dto.OrderDTO;
import com.revature.watercanapporderms.model.Order;
import com.revature.watercanapporderms.service.OrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	@PostMapping("orderCan")
	@ApiOperation("OrderApi")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Order Success", response = Message.class),
			@ApiResponse(code = 400, message = "Order failed") })
	public ResponseEntity<Object> orderCans(@RequestBody OrderDTO orderDTO) {
		String errorMessage = null;
		Message message = null;
		String status = null;
		Order result =null;
		try {
			
			result=orderService.orderStock(orderDTO);
			status = "Success";
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		if (status != null) {
			message = new Message(status);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} else {
			message = new Message(errorMessage);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

	}
	}
