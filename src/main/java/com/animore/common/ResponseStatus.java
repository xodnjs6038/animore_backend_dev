package com.animore.common;

import lombok.Getter;

@Getter
public enum ResponseStatus {
	SUCCESS(200, "요청이 성공적으로 처리되었습니다."),
	BAD_REQUEST(400, "잘못된 요청입니다."),
	UNAUTHORIZED(401, "권한이 없습니다."),
	NOT_FOUND(404, "리소스를 찾을 수 없습니다."),
	INTERNAL_SERVER_ERROR(500, "서버 내부 오류가 발생했습니다.");

	private final int statusCode;
	private final String message;

	ResponseStatus(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

}
