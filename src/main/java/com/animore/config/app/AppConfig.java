package com.animore.config.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.animore.mapper.UserMapper;
import com.animore.mapper.UserMapperImpl;
import com.animore.service.JwtService;
import com.animore.service.UserService;

@Configuration
public class AppConfig {
	@Bean
	public JwtService jwtService() {
		return new JwtService();
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
