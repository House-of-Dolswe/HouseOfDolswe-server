package com.houseofdolswe.HouserOfDolswe_server.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserInfoResponseDTO {
	@JsonProperty("id")
	private Long id;

	// @JsonProperty("kakao_account")
	// private KakaoAccount kakaoAccount;
	//
	// @Getter
	// @NoArgsConstructor
	// @JsonIgnoreProperties(ignoreUnknown = true)
	// public static class KakaoAccount {
	//    @JsonProperty("email")
	//    private String email;
	//
	//    // true - 유효한 이메일, false - 만료된 이메일
	//    @JsonProperty("is_email_valid")
	//    private Boolean emailValid;
	// }
}
