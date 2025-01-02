package com.animore.auth.controller;

import java.util.List;

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
import com.animore.common.ApiResponse;
import com.animore.common.ResponseUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;

	@GetMapping("users")
	public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
		List<User> data = userService.getAllUsers();
		List<UserDto> result = userMapper.toDtoList(data);
		return ResponseUtil.success(result);
	}

	@GetMapping("user/{id}")
	public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long id) {
		User data = userService.getUserById(id);
		UserDto result = userMapper.toDto(data);
		return ResponseUtil.success(result);
	}

	@PostMapping("user/create")
	public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody CreateUserDto userDto) {
		User createdUser = userService.createUser(userDto);
		UserDto result = userMapper.toDto(createdUser);
		return ResponseUtil.success(result);
	}

	@PutMapping("user/{id}")
	public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable Long id,
		@RequestBody UpdateUserDto updateUserDto) {
		User updatedUser = userService.updateUser(id, updateUserDto);
		UserDto result = userMapper.toDto(updatedUser);
		return ResponseUtil.success(result);
	}

	@DeleteMapping("user/{id}")
	public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseUtil.success("OK");
	}
}
