package com.houseofdolswe.HouserOfDolswe_server.service.command;

import com.houseofdolswe.HouserOfDolswe_server.domain.enums.Age;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.Call;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.House;

public record OnboardingCommand(
	House house, Call call, Age age
) {
}
