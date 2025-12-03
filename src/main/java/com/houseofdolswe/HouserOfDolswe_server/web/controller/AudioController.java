package com.houseofdolswe.HouserOfDolswe_server.web.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.ApiResponse;
import com.houseofdolswe.HouserOfDolswe_server.service.AudioCommandService;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.PostLikeResponseDTO;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.StatusResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/audios")
public class AudioController {
	private final AudioCommandService audioCommandService;

	@PostMapping("/{audio-id}/like")
	public ApiResponse<PostLikeResponseDTO> postLike(
		@PathVariable("audio-id") Long audioId
	) {
		Long userId = 1L;
		return ApiResponse.onSuccess(audioCommandService.postLike(userId, audioId));
	}

	@DeleteMapping("/{audio-id}/like")
	public ApiResponse<StatusResponseDTO> deleteLike(
		@PathVariable("audio-id") Long audioId
	) {
		Long userId = 1L;
		return ApiResponse.onSuccess(audioCommandService.deleteLike(userId, audioId));
	}
}
