package com.animore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.animore.auth.application.AuthService;
import com.animore.auth.application.UserService;
import com.animore.auth.mapper.UserMapper;
import com.animore.auth.mapper.UserMapperImpl;

@Configuration
public class AppConfig {
	@Bean
	public AuthService authService() {
		return new AuthService();
	}

	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public UserMapper userMapper() {
		return new UserMapperImpl();
	}
}
