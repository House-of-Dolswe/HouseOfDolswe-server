package com.houseofdolswe.HouserOfDolswe_server.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.ApiResponse;
import com.houseofdolswe.HouserOfDolswe_server.converter.OnboardingConverter;
import com.houseofdolswe.HouserOfDolswe_server.service.OnboardingCommandService;
import com.houseofdolswe.HouserOfDolswe_server.service.command.OnboardingCommand;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.OnboardingRequestDTO;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.OnboardingResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/onboarding")
public class OnboardingRestController {
	private final OnboardingConverter onboardingConverter;
	private final OnboardingCommandService onboardingCommandService;

	@PostMapping
	public ApiResponse<OnboardingResponseDTO> postOnboarding(
		@Valid @RequestBody OnboardingRequestDTO onboardingRequestDTO
	) {
		Long userId = 1L; // 카카오 로그인 구현 전이므로 임시 유저 ID로 구현
		OnboardingCommand onboardingCommand = onboardingConverter.toOnboarding(onboardingRequestDTO);
		return ApiResponse.onSuccess(onboardingCommandService.postOnboarding(userId, onboardingCommand));
	}
}
