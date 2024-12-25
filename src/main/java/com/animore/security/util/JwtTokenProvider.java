package com.animore.security.util;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.animore.auth.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

	private final JwtProperties jwtProperties;
	private final SecretKey secretKey;

	public JwtTokenProvider(JwtProperties jwtProperties) {
		this.jwtProperties = jwtProperties;
		this.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
	}

	// 토큰 생성
	public String createToken(String email, String roles) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtProperties.getExpiration());

		return Jwts.builder()
			.setSubject(email)
			.claim("roles", roles) // 권한 정보 추가
			.setIssuedAt(now)
			.setExpiration(expiryDate)
			.signWith(secretKey, SignatureAlgorithm.HS512)
			.compact();
	}

	// 토큰에서 인증 정보 추출
	public Authentication getAuthentication(String token) {
		String email = getEmail(token); // 토큰에서 이메일 추출
		List<GrantedAuthority> authorities = getAuthorities(token); // 토큰에서 권한 추출

		// User 엔터티 생성
		User userDetails = User.builder()
			.email(email)
			.roles(authorities.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList())) // 권한을 문자열로 변환하여 User에 저장
			.build();

		// 인증 객체 생성
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

	// 토큰에서 사용자 이메일 추출
	public String getEmail(String token) {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}

	// 토큰에서 권한 정보 추출
	public List<GrantedAuthority> getAuthorities(String token) {
		String role = getRole(token);  // 단일 역할을 가져옴
		return List.of(new SimpleGrantedAuthority(role));  // 단일 역할을 권한으로 변환
	}

	private String getRole(String token) {
		Claims claims = getClaims(token);
		return (String)claims.get("role");
	}

	// Request Header에서 토큰 추출
	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	// 토큰 유효성 검증
	public boolean validateToken(String token) {
		try {
			getClaims(token); // 유효하지 않은 토큰이면 예외 발생
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 토큰에서 Claims 추출
	private Claims getClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}
}
