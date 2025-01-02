package com.animore.auth.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.animore.auth.domain.User;
import com.animore.auth.domain.UserRepository;
import com.animore.auth.dto.CreateUserDto;
import com.animore.auth.dto.UpdateUserDto;
import com.animore.auth.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public User createUser(CreateUserDto createUserDto) {
		User user = userMapper.toEntity(createUserDto);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserCode(createUserCode());
		return userRepository.save(user);
	}

	public User updateUser(Long id, UpdateUserDto updateUserDto) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("User Not Found"));

		if (updateUserDto.getName() != null) {
			user.setName(updateUserDto.getName());
		}

		if (updateUserDto.getPhoneNumber() != null) {
			user.setPhoneNumber(updateUserDto.getPhoneNumber());
		}

		if (updateUserDto.getSubwayId() != null) {
			user.setSubwayId(updateUserDto.getSubwayId());
		}

		if (updateUserDto.getUseCar() != null) {
			user.setUseCar(updateUserDto.getUseCar());
		}

		return userRepository.save(user);
	}

	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("User Not Found"));
		userRepository.delete(user);
	}

	private String createUserCode() {
		String userCodePrefix = "Ani";
		String userCode;
		User existingUser;
		int randomNumber;

		do {
			randomNumber = (int)(Math.random() * 900) + 100;
			userCode = userCodePrefix + randomNumber;

			existingUser = userRepository.findByUserCode(userCode);

		} while (existingUser != null);

		return userCode;
	}
}
