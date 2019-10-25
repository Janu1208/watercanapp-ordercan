package com.revature.watercanapporderms.dto;

import lombok.Data;

@Data
public class Message {
	private String message;
    public String getMessage() {
        return message;
    }
    public Message(String message) {
        super();
        this.message = message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
