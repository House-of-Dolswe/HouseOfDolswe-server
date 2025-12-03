package com.houseofdolswe.HouserOfDolswe_server.converter;

import org.springframework.stereotype.Component;

import com.houseofdolswe.HouserOfDolswe_server.service.command.OnboardingCommand;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.OnboardingRequestDTO;

@Component
public class OnboardingConverter {
	public OnboardingCommand toOnboarding(OnboardingRequestDTO onboardingRequestDTO) {
		return new OnboardingCommand(onboardingRequestDTO.house(), onboardingRequestDTO.call(), onboardingRequestDTO.age());
	}
}
