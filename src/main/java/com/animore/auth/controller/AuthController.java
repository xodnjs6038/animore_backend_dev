package com.animore.auth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.animore.auth.application.AuthService;
import com.animore.auth.dto.PostAuthDto;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping
	public ResponseEntity<String> postAuth(@RequestBody PostAuthDto postAuthDto) {
		String token = authService.postAuth(postAuthDto);
		return ResponseEntity.ok(Map.of("token", token).toString());
	}
}
