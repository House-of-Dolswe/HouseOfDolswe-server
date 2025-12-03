package com.houseofdolswe.HouserOfDolswe_server.web.dto;

public record KakaoLoginResponseDTO(
	Long userId, String accessToken,
	String refreshToken, boolean onboardingRequired
) {
}