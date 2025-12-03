package com.houseofdolswe.HouserOfDolswe_server.service;

import com.houseofdolswe.HouserOfDolswe_server.web.dto.KakaoLoginResponseDTO;

public interface KakaoLoginQueryService {
	String getKakaoAccessToken(String code);
	Long getKakaoId(String accessToken);
	KakaoLoginResponseDTO kakaoLogin(String code);
}
