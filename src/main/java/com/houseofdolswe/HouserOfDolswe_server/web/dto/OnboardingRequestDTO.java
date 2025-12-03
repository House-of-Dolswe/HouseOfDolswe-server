package com.houseofdolswe.HouserOfDolswe_server.web.dto;

import com.houseofdolswe.HouserOfDolswe_server.domain.enums.Age;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.Call;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.House;

public record OnboardingRequestDTO(
	House house, Call call, Age age
) {
}
