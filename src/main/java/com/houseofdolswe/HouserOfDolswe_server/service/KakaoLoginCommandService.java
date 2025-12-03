package com.houseofdolswe.HouserOfDolswe_server.service;

public interface KakaoLoginCommandService {
	String getKakaoAccessToken(String code);
	Long getKakaoId(String accessToken);
}
