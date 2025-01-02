package com.animore.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.animore.common.ApiResponse;
import com.animore.common.ResponseStatus;
import com.animore.common.ResponseUtil;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {
		return ResponseUtil.error(ResponseStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	}
}
