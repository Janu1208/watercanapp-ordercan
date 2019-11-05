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
	
	public List<StockDTO> findAllStocks(){		
        ResponseEntity<StockDTO[]> getForEntity = restTemplate.getForEntity(apiUrl+"/viewStock", StockDTO[].class);
        StockDTO[] stockList = getForEntity.getBody();
        List<StockDTO> list = new ArrayList<StockDTO>();
        for (StockDTO stockDTO : stockList) {
			list.add(stockDTO);
		}
		return list;
	}
	
	void  addStocks(final OrderDTO orderDto){		
		System.out.println(orderDto);
        ResponseEntity<String> postForEntity = restTemplate.postForEntity(apiUrl+"/updateOrderedCans",orderDto, String.class);
        System.out.println(postForEntity.getBody());
	}
}
