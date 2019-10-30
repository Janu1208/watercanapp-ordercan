package com.revature.watercanapporderms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.revature.watercanapporderms.dto.OrderDTO;
import com.revature.watercanapporderms.dto.StockDTO;

@Service
public class StockService {
	
	@Autowired
	private RestTemplate restTemplate;
	

	String apiUrl = "https://watercansapp-stock-ms.herokuapp.com/";
	
	public List<StockDTO> findAllStocks(){		
        ResponseEntity<List> getForEntity = restTemplate.getForEntity(apiUrl+"/viewStock", List.class);
        List<StockDTO> stockList = getForEntity.getBody();
		return stockList;
	}
	
	void  addStocks(final OrderDTO orderDto){		
        ResponseEntity<Void> postForEntity = restTemplate.postForEntity(apiUrl+"/updateOrderedCans",orderDto, void.class);
        System.out.println(postForEntity);
	}
}
