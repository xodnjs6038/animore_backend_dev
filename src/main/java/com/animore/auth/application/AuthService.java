package com.animore.auth.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.animore.auth.domain.User;
import com.animore.auth.domain.UserRepository;
import com.animore.auth.dto.PostAuthDto;
import com.animore.auth.infrastructure.JwtUtil;
import com.animore.exception.ErrorCode;
import com.animore.exception.ResponseException;

@Service
public class AuthService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	public String postAuth(PostAuthDto postAuthDto) {
		User user = userRepository.findByEmail(postAuthDto.getEmail());
		if (user == null) {
			throw new ResponseException(ErrorCode.INVALID_EMAIL);
		}

		if (!BCrypt.checkpw(postAuthDto.getPassword(), user.getPassword())) {
			throw new ResponseException(ErrorCode.INVALID_PASSWORD);
		}

		return jwtUtil.generateToken(user.getId());
	}
}
