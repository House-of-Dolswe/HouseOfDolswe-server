package com.houseofdolswe.HouserOfDolswe_server.web.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.ApiResponse;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.code.status.ErrorStatus;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.handler.AuthHandler;
import com.houseofdolswe.HouserOfDolswe_server.config.JwtTokenProvider;
import com.houseofdolswe.HouserOfDolswe_server.service.AudioCommandService;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.PostLikeResponseDTO;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.StatusResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/audios")
public class AudioController {
	private final JwtTokenProvider jwtTokenProvider;
	private final AudioCommandService audioCommandService;

	@PostMapping("/{audio-id}/like")
	public ApiResponse<PostLikeResponseDTO> postLike(
		@RequestHeader(value = "Authorization", required = false) String authorization,
		@PathVariable("audio-id") Long audioId
	) {
		// RequestHeader 내 Authorization값 확인
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			throw new AuthHandler(ErrorStatus.MISSING_ACCESS_TOKEN);
		}

		// Bearer 제외하고 토큰값 추출
		String accessToken = authorization.substring(7);

		// 유효성 검사
		// 만료되었거나 유효하지 않은 토큰이면 에러 발생시킴
		if (!jwtTokenProvider.validateToken(accessToken, false)) {
			throw new AuthHandler(ErrorStatus.INVALID_OR_EXPIRED_TOKEN);
		}

		Long userId = jwtTokenProvider.getUserId(accessToken, false);

		return ApiResponse.onSuccess(audioCommandService.postLike(userId, audioId));
	}

	@DeleteMapping("/{audio-id}/like")
	public ApiResponse<StatusResponseDTO> deleteLike(
		@RequestHeader(value = "Authorization", required = false) String authorization,
		@PathVariable("audio-id") Long audioId
	) {
		// RequestHeader 내 Authorization값 확인
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			throw new AuthHandler(ErrorStatus.MISSING_ACCESS_TOKEN);
		}

		// Bearer 제외하고 토큰값 추출
		String accessToken = authorization.substring(7);

		// 유효성 검사
		// 만료되었거나 유효하지 않은 토큰이면 에러 발생시킴
		if (!jwtTokenProvider.validateToken(accessToken, false)) {
			throw new AuthHandler(ErrorStatus.INVALID_OR_EXPIRED_TOKEN);
		}

		Long userId = jwtTokenProvider.getUserId(accessToken, false);

		return ApiResponse.onSuccess(audioCommandService.deleteLike(userId, audioId));
	}
}
