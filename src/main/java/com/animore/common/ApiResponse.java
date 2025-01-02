package com.animore.common;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
	private int statusCode;
	private String message;
	private T data;
	private LocalDateTime timestamp = LocalDateTime.now();

	public ApiResponse(ResponseStatus status, T data) {
		this.statusCode = status.getStatusCode();
		this.message = status.getMessage();
		this.data = data;
		this.timestamp = LocalDateTime.now();
	}

	public ApiResponse(ResponseStatus status) {
		this.statusCode = status.getStatusCode();
		this.message = status.getMessage();
		this.timestamp = LocalDateTime.now();
	}
}
