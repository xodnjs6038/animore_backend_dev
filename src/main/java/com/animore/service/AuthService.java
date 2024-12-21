package com.animore.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.animore.dto.auth.request.PostAuthDto;
import com.animore.model.User;
import com.animore.repository.UserRepository;

@Service
public class AuthService {

	private UserRepository userRepository;

	public User postAuth(PostAuthDto postAuthDto) {
		User user = userRepository.findByEmail(postAuthDto.getEmail());
		if (user == null) {
			throw new RuntimeException("Invalid email");
		}

		if (!BCrypt.checkpw(postAuthDto.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		return user;
	}
}
