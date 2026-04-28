package com.example.SimplestCRUDExample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ErrorResponse {
	private int status;
	private String message;
	private long timestamp;
	private String details;

	public ErrorResponse(int status, String message, String details) {
		this.status = status;
		this.message = message;
		this.details = details;
		this.timestamp = System.currentTimeMillis();
	}
}
