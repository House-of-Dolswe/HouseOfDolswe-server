package com.houseofdolswe.HouserOfDolswe_server.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatusCode;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.code.status.ErrorStatus;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.handler.KakaoHandler;
import com.houseofdolswe.HouserOfDolswe_server.config.JwtTokenProvider;
import com.houseofdolswe.HouserOfDolswe_server.domain.User;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.Status;
import com.houseofdolswe.HouserOfDolswe_server.repository.UserRepository;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.KakaoLoginResponseDTO;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.KakaoTokenResponseDTO;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.KakaoUserInfoResponseDTO;

import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KakaoLoginQueryServiceImpl implements KakaoLoginQueryService {

	private final JwtTokenProvider jwtTokenProvider;
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

	// 실제 서비스에서의 카카오 로그인
	public KakaoLoginResponseDTO kakaoLogin(String code) {
		String kakaoAccessToken = getKakaoAccessToken(code);
		Long kakaoId = getKakaoId(kakaoAccessToken);

		Optional<User> existingUser = userRepository.findByKakaoId(kakaoId);

		// kakaoId값을 통해 신규 유저인지 기존 유저인지 확인
		// 신규 유저일 경우 userRepository에 새롭게 저장함
		User user = userRepository.findByKakaoId(kakaoId)
			.orElseGet(() -> userRepository.save(User.builder()
				.kakaoId(kakaoId)
				.onboardingStatus(Status.NOT_STARTED)
				.build()));
		// 가입은 했으나 온보딩 설문조사 도중 어플 강제종료 등의 사유로 온보딩값이 저장되지 않은 경우에는
		// onboardingRequired 값을 false로 반환함
		boolean onboardingRequired = (user.getOnboardingStatus() == Status.COMPLETED) ? false : true;

		String accessToken = jwtTokenProvider.createAccessToken(user.getUserId());
		String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId());

		user.setAccessToken(accessToken);
		user.setRefreshToken(refreshToken);
		user.setRefreshTokenExpiry(LocalDateTime.now().plusDays(14));

		userRepository.save(user);

		return new KakaoLoginResponseDTO(
			user.getUserId(),
			accessToken,
			refreshToken,
			onboardingRequired
		);
	}
}