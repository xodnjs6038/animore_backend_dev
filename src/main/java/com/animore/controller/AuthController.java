package com.animore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.animore.dto.auth.request.PostAuthDto;
import com.animore.model.User;
import com.animore.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping
	public ResponseEntity<String> postAuth(@RequestBody PostAuthDto postAuthDto) {
		User getAuth = authService.postAuth(postAuthDto);
		return ResponseEntity.ok().body(getAuth.toString());
	}
}
