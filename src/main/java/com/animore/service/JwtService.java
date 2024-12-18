package com.animore.service;

import static com.animore.exception.ErrorCode.*;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.animore.exception.ResponseException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	/*
	JWT Create
	@param userIdx
	@return String
	 */
	public String createJwt(Long userIdx) {
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
    Header에서 X-ACCESS-TOKEN 으로 JWT 추출
    @return String
     */
	public String getJwt() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		return request.getHeader("Authorization");
	}

	/*
    JWT에서 userIdx 추출
    @return int
    @throws BaseException
     */
	public Long getUserId() throws ResponseException {
		//1. JWT 추출
		String accessToken = getJwt();
		if (accessToken == null || accessToken.isEmpty()) {
			throw new ResponseException(EMPTY_JWT);
		}

		// 2. JWT parsing
		Jws<Claims> claims;
		try {
			claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(accessToken);
		} catch (Exception ignored) {
			throw new ResponseException(INVALID_JWT);
		}

		// 3. userIdx 추출
		return claims.getBody().get("userIdx", Long.class);  // jwt 에서 userIdx를 추출합니다.
	}
}
