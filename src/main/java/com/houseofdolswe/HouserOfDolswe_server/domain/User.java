package com.houseofdolswe.HouserOfDolswe_server.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.houseofdolswe.HouserOfDolswe_server.domain.common.BaseEntity;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "kakao_id", nullable = false)
	private Long kakaoId;

	@Setter
	@Enumerated(EnumType.STRING)
	@Column(name = "onboarding_status", nullable = false, length = 20)
	private Status onboardingStatus = Status.NOT_STARTED;

	@Setter
	@Column(name = "access_token")
	private String accessToken;

	@Setter
	@Column(name = "refresh_token")
	private String refreshToken;

	@Setter
	@Column(name = "refresh_token_expiry")
	private LocalDateTime refreshTokenExpiry;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserAudioLike> likes = new ArrayList<>();
}
