package com.animore.auth.application;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.animore.auth.domain.User;
import com.animore.auth.domain.UserRepository;
import com.animore.auth.dto.PostAuthDto;
import com.animore.auth.infrastructure.JwtUtil;

@Service
public class AuthService {

	private final JwtUtil jwtUtil;
	private UserRepository userRepository;

	public AuthService(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	public String postAuth(PostAuthDto postAuthDto) {
		User user = userRepository.findByEmail(postAuthDto.getEmail());
		if (user == null) {
			throw new RuntimeException("Invalid email");
		}

		if (!BCrypt.checkpw(postAuthDto.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		return jwtUtil.generateToken(user.getId());
	}
}
