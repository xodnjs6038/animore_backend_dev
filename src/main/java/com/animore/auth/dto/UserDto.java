package com.animore.auth.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	private Long id;
	private String userCode;
	private String email;
	private String password;
	private String type;
	private Long status;
	private String name;
	private String phoneNumber;
	private Long subwayId;
	private Long useCar;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}