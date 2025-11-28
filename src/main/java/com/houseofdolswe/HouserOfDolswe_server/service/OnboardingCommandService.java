package com.houseofdolswe.HouserOfDolswe_server.service;

import com.houseofdolswe.HouserOfDolswe_server.service.command.OnboardingCommand;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.OnboardingResponseDTO;

public interface OnboardingCommandService {
	OnboardingResponseDTO postOnboarding(Long userId, OnboardingCommand onboardingCommand);
}
