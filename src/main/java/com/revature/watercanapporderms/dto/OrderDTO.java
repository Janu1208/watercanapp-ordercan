package com.revature.watercanapporderms.dto;

import lombok.Data;

@Data
public class OrderDTO {
	
	private int id;
	private int userId;
	private String userName;
	private Integer orderCans;
	}
