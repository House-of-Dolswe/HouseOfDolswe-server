package com.houseofdolswe.HouserOfDolswe_server.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.ApiResponse;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.code.status.ErrorStatus;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.handler.AuthHandler;
import com.houseofdolswe.HouserOfDolswe_server.config.JwtTokenProvider;
import com.houseofdolswe.HouserOfDolswe_server.converter.OnboardingConverter;
import com.houseofdolswe.HouserOfDolswe_server.service.OnboardingCommandService;
import com.houseofdolswe.HouserOfDolswe_server.service.command.OnboardingCommand;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.OnboardingRequestDTO;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.StatusResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/onboarding")
public class OnboardingController {
	private final JwtTokenProvider jwtTokenProvider;
	private final OnboardingConverter onboardingConverter;
	private final OnboardingCommandService onboardingCommandService;

	@PostMapping
	public ApiResponse<StatusResponseDTO> postOnboarding(
		@RequestHeader(value = "Authorization", required = false) String authorization,
		@Valid @RequestBody OnboardingRequestDTO onboardingRequestDTO
	) {
		// RequestHeader 내 Authorization값 확인
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			throw new AuthHandler(ErrorStatus.MISSING_ACCESS_TOKEN);
		}

		// Bearer 제외하고 토큰값 추출
		String accessToken = authorization.substring(7);

		// 유효성 검사
		// 만료되었거나 유효하지 않은 토큰이면 에러 발생시킴
		if (!jwtTokenProvider.validateToken(accessToken, false)) {
			throw new AuthHandler(ErrorStatus.INVALID_OR_EXPIRED_TOKEN);
		}

		Long userId = jwtTokenProvider.getUserId(accessToken, false);

		OnboardingCommand onboardingCommand = onboardingConverter.toOnboarding(onboardingRequestDTO);
		return ApiResponse.onSuccess(onboardingCommandService.postOnboarding(userId, onboardingCommand));
	}
}
