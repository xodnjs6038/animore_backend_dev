package com.animore.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	EMPTY_JWT("JWT is missing", 1001),
	INVALID_JWT("JWT is invalid", 1002);

	private final String message;
	private final int code;

	ErrorCode(String message, int code) {
		this.message = message;
		this.code = code;
	}

}
