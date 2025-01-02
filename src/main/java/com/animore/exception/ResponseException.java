package com.animore.exception;

import lombok.Getter;

@Getter
public class ResponseException extends RuntimeException {
	private final ErrorCode errorCode;

	public ResponseException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

}
