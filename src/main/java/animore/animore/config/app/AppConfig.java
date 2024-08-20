package animore.animore.config.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import animore.animore.mapper.UserMapper;
import animore.animore.mapper.UserMapperImpl;
import animore.animore.service.UserService;

@Configuration
public class AppConfig {
	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public UserMapper userMapper() {
		return new UserMapperImpl();
	}
}
