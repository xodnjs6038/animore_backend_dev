package com.animore.config.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.animore.mapper.UserMapper;
import com.animore.mapper.UserMapperImpl;
<<<<<<< HEAD
=======
import com.animore.service.JwtService;
>>>>>>> c13da433582b3a11477c56b65d1ed353e827709e
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
