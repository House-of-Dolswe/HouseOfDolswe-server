package com.houseofdolswe.HouserOfDolswe_server.service;

import org.springframework.stereotype.Service;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.code.status.ErrorStatus;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.handler.UserHandler;
import com.houseofdolswe.HouserOfDolswe_server.domain.Onboarding;
import com.houseofdolswe.HouserOfDolswe_server.domain.User;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.Age;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.Call;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.House;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.Status;
import com.houseofdolswe.HouserOfDolswe_server.repository.OnboardingRepository;
import com.houseofdolswe.HouserOfDolswe_server.repository.UserRepository;
import com.houseofdolswe.HouserOfDolswe_server.service.command.OnboardingCommand;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.OnboardingResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OnboardingCommandServiceImpl implements OnboardingCommandService {

	private final UserRepository userRepository;
	private final OnboardingRepository onboardingRepository;

	@Override
	public OnboardingResponseDTO postOnboarding(Long userId, OnboardingCommand onboardingCommand) {
		User user = userRepository.findByUserId(userId)
			.orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

		House house = onboardingCommand.house();
		Call call = onboardingCommand.call();
		Age age = onboardingCommand.age();

		Onboarding onboarding = Onboarding.builder()
			.user(user)
			.house(house)
			.call(call)
			.age(age)
			.build();

		user.setOnboardingStatus(Status.COMPLETED);

		onboardingRepository.save(onboarding);
		userRepository.save(user);

		return new OnboardingResponseDTO("completed");
	}
}
