package com.revature.watercanapporderms.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StockDTO {
	private Integer stockId;
	private LocalDate dateTime;
	private Integer availableCans;

}
