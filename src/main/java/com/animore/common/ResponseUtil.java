package com.animore.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
	public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
		return ResponseEntity.ok(new ApiResponse<>(ResponseStatus.SUCCESS, data));
	}

	public static ResponseEntity<ApiResponse<Object>> error(ResponseStatus status) {
		return ResponseEntity.status(HttpStatus.valueOf(status.getStatusCode()))
			.body(new ApiResponse<>(status, null));
	}

	public static ResponseEntity<ApiResponse<Object>> error(ResponseStatus status, String customMessage) {
		ApiResponse<Object> response = new ApiResponse<>(status);
		response.setMessage(customMessage);
		return ResponseEntity.status(HttpStatus.valueOf(status.getStatusCode())).body(response);
	}
}
