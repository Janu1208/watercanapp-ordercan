package com.revature.watercanapporderms.service;

import java.util.ArrayList;
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
	
	public ArrayList<StockDTO> findAllStocks(){		
        ResponseEntity<List> getForEntity = restTemplate.getForEntity(apiUrl+"/viewStock", List.class);
        ArrayList<StockDTO> stockList = (ArrayList<StockDTO>) getForEntity.getBody();
        System.out.println(stockList);
		return stockList;
	}
	
	void  addStocks(final OrderDTO orderDto){		
        ResponseEntity<Void> postForEntity = restTemplate.postForEntity(apiUrl+"/updateOrderedCans",orderDto, void.class);
        System.out.println(postForEntity);
	}
}
