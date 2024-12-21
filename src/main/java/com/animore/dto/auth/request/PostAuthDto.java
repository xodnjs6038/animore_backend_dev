package com.animore.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PostAuthDto {
	@NotEmpty
	@Email
	private String email;
	@NotBlank
	private String password;
}
