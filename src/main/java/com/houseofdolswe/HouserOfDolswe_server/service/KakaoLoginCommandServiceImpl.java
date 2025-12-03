package com.houseofdolswe.HouserOfDolswe_server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatusCode;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.code.status.ErrorStatus;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.handler.KakaoHandler;
import com.houseofdolswe.HouserOfDolswe_server.repository.UserRepository;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.KakaoTokenResponseDTO;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.KakaoUserInfoResponseDTO;

import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KakaoLoginCommandServiceImpl implements KakaoLoginCommandService {

	@Value("${kakao.auth.client}")
	private String clientId;
	@Value("${kakao.auth.accessTokenURL}")
	private String KakaoAccessTokenURL;
	@Value("${kakao.auth.userInfoURL}")
	private String KakaoUserInfoURL;

	private final UserRepository userRepository;

	// 인가코드를 사용해 로그인할 유저의 카카오 액세스 토큰값을 받아옴
	public String getKakaoAccessToken(String code) {
		KakaoTokenResponseDTO kakaoTokenResponseDTO = WebClient.create(KakaoAccessTokenURL).post()
			.uri(uriBuilder -> uriBuilder
				.scheme("https")
				.queryParam("grant_type", "authorization_code")
				.queryParam("client_id", clientId)
				.queryParam("code", code)
				.build(true))
			.header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new KakaoHandler(ErrorStatus.KAKAO_INVALID_REQUEST)))
			.onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new KakaoHandler(ErrorStatus.KAKAO_SERVER_ERROR)))
			.bodyToMono(KakaoTokenResponseDTO.class)
			.block();

		return kakaoTokenResponseDTO.getAccessToken();
	}

	// 카카오 액세스 토큰을 사용해 유저의 정보값을 받아옴
	public Long getKakaoId(String accessToken) {
		KakaoUserInfoResponseDTO kakaoUserInfoResponseDTO = WebClient.create(KakaoUserInfoURL)
			.get()
			.uri(uriBuilder -> uriBuilder
				.scheme("https")
				.build(true))
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // access token 인가
			.header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new KakaoHandler(ErrorStatus.KAKAO_INVALID_REQUEST)))
			.onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new KakaoHandler(ErrorStatus.KAKAO_SERVER_ERROR)))
			.bodyToMono(KakaoUserInfoResponseDTO.class)
			.block();

		return kakaoUserInfoResponseDTO.getId();
	}
}
