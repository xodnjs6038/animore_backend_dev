package com.animore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.animore.auth.infrastructure.JwtAuthenticationFilter;
import com.animore.auth.infrastructure.JwtUtil;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtUtil jwtUtil) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable) // csrf 보호 비활성화
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(
					"/swagger-ui/**", "/v3/api-docs/**",
					"/api/auth/**", "/api/user/create"
				).permitAll()
				.anyRequest().authenticated() // 나머지 요청은 인증 필요
			)
			.addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
