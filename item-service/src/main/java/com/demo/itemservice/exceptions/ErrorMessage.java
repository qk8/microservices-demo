package com.demo.itemservice.exceptions;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {

	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;
	

}
