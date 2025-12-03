package com.houseofdolswe.HouserOfDolswe_server.apiPayload.code.status;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.code.BaseErrorCode;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

	// 가장 일반적인 응답
	_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
	_BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
	_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
	_FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER4001", "사용자를 찾을 수 없습니다."),

	KAKAO_INVALID_REQUEST(HttpStatus.BAD_REQUEST, "KAKAO4001", "카카오 API 요청 파라미터가 잘못되었습니다."),
	KAKAO_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "KAKAO5001", "카카오 서버에서 오류가 발생했습니다."),

	AUDIO_NOT_FOUND(HttpStatus.NOT_FOUND, "AUDIO4001", "오디오를 찾을 수 없습니다."),

	LIKE_ALREADY_EXISTS(HttpStatus.CONFLICT, "LIKE4001", "이미 좋아요를 눌렀습니다"),

	INVALID_OR_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH4001", "유효하지 않거나 만료된 토큰입니다."),
	MISSING_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH4002", "Access Token이 존재하지 않습니다."),

	ONBOARDING_ALREADY_EXISTS(HttpStatus.CONFLICT, "ONBOARDING4001", "이미 온보딩 설문값이 저장되어있는 사용자입니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	@Override
	public ErrorReasonDTO getReason() {
		return ErrorReasonDTO.builder()
			.message(message)
			.code(code)
			.isSuccess(false)
			.build();
	}

	@Override
	public ErrorReasonDTO getReasonHttpStatus() {
		return ErrorReasonDTO.builder()
			.message(message)
			.code(code)
			.isSuccess(false)
			.httpStatus(httpStatus)
			.build()
			;
	}
}