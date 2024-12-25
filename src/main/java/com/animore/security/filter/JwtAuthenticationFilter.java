package com.animore.security.filter;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.animore.security.util.JwtTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {

		// Request Header에서 JWT 토큰을 추출
		String token = jwtTokenProvider.resolveToken(request);

		// 토큰 유효성 검증
		if (token != null && jwtTokenProvider.validateToken(token)) {
			// 토큰이 유효하면 사용자 인증 정보를 가져와 SecurityContext에 저장
			SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(token));
		}

		// 다음 필터로 요청 전달
		filterChain.doFilter(request, response);
	}
}
