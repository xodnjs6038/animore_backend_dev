package com.animore.auth.infrastructure;

import static com.animore.exception.ErrorCode.*;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.animore.exception.ResponseException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	/*
	JWT Token Create
	@param userIdx
	@return String
	 */
	public String generateToken(Long userIdx) {
		Date now = new Date();
		return Jwts.builder()
			.setHeaderParam("type", "jwt")
			.claim("userIdx", userIdx)
			.setIssuedAt(now)
			.setExpiration(new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 365)))
			.signWith(key)
			.compact();
	}

	/*
	JWT Claims Return
	@return Claims
	 */
	public Claims extractClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	/*
	JWT Validate Result
	@return boolean
	 */
	public Authentication validateToken(String token) {
		try {
			Claims claims = extractClaims(token);
			Date expiration = claims.getExpiration();
			if (expiration.before(new Date())) {
				throw new RuntimeException("Token has expired");
			}
			String username = claims.getSubject();

			return new UsernamePasswordAuthenticationToken(
				username,
				null,
				Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
			);
		} catch (Exception e) {
			throw new RuntimeException("Invalid token: " + e.getMessage());
		}
	}

	/*
	Header X-ACCESS-TOKEN Return
	@return String
	 */
	public String getJwt() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		return request.getHeader("Authorization");
	}

	/*
	JWT userIdx Return
	@return int
	@throws BaseException
	 */
	public Long getUserIdx() throws ResponseException {
		// 1. JWT validation
		String accessToken = getJwt();
		if (accessToken == null || accessToken.isEmpty()) {
			throw new ResponseException(EMPTY_JWT);
		}

		// 2. JWT parsing
		Jws<Claims> claims;
		try {
			claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken);
		} catch (Exception ignored) {
			throw new ResponseException(INVALID_JWT);
		}

		// 3. userIdx return
		return claims.getBody().get("userIdx", Long.class);
	}
}
