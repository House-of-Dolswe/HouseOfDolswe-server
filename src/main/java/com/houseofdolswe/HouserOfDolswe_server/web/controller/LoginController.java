package com.houseofdolswe.HouserOfDolswe_server.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.ApiResponse;
import com.houseofdolswe.HouserOfDolswe_server.service.KakaoLoginQueryService;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.KakaoLoginResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

	private final KakaoLoginQueryService kakaoLoginQueryService;

	@GetMapping("/kakao")
	public ApiResponse<KakaoLoginResponseDTO> kakaoLogin(
		@RequestParam String code
	) {
		return ApiResponse.onSuccess(kakaoLoginQueryService.kakaoLogin(code));
	}
}
