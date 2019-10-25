package com.revature.watercanapporderms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.watercanapporderms.service.OrderService;

@Configuration
public class AppConfig {
	@Bean
	public OrderService orderService() {
		return new OrderService();
	}
}