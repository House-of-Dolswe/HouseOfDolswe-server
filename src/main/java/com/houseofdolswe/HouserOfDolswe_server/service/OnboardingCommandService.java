package com.houseofdolswe.HouserOfDolswe_server.service;

import com.houseofdolswe.HouserOfDolswe_server.service.command.OnboardingCommand;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.StatusResponseDTO;

public interface OnboardingCommandService {
	StatusResponseDTO postOnboarding(Long userId, OnboardingCommand onboardingCommand);
}
