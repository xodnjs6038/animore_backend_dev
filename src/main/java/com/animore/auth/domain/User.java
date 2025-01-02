package com.animore.auth.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "ani_users")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userCode;
	private String email;
	private String password;
	@Builder.Default
	private String type = "V";
	@Builder.Default
	private Long status = 1L;
	private String name;
	private String phoneNumber;
	private Long subwayId;
	@Builder.Default
	private Long useCar = 0L;
	@Builder.Default
	private LocalDateTime createdDate = LocalDateTime.now();
	private LocalDateTime updatedDate;

	@ElementCollection(fetch = FetchType.EAGER)
	@Builder.Default
	private Collection<String> roles = List.of("ROLE_USER");

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
			.map(role -> (GrantedAuthority)() -> role)
			.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; // 필요 시 사용자 상태에 따라 변경
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.status == 1L; // 예시: 1일 경우 활성화된 계정
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // 필요 시 인증 정보 상태에 따라 변경
	}

	@Override
	public boolean isEnabled() {
		return this.status == 1L; // 예시: 1일 경우 활성화된 계정
	}
}
