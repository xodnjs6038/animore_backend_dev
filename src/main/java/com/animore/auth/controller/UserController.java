package com.animore.auth.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.animore.auth.application.UserService;
import com.animore.auth.domain.User;
import com.animore.auth.dto.CreateUserDto;
import com.animore.auth.dto.UpdateUserDto;
import com.animore.auth.dto.UserDto;
import com.animore.auth.mapper.UserMapper;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;

	@GetMapping
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers().stream()
			.map(user -> userMapper.toDto(user))
			.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
		return userService.getUserById(id)
			.map(user -> ResponseEntity.ok().body(userMapper.toDto(user)))
			.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/create")
	public UserDto createUser(@RequestBody CreateUserDto userDto) {
		User createdUser = userService.createUser(userDto);
		return userMapper.toDto(createdUser);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
		return ResponseEntity.ok(userMapper.toDto(userService.updateUser(id, updateUserDto)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
}
