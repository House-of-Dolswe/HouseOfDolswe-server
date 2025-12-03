package com.houseofdolswe.HouserOfDolswe_server.service;

import org.springframework.stereotype.Service;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.code.status.ErrorStatus;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.handler.AudioHandler;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.handler.UserHandler;
import com.houseofdolswe.HouserOfDolswe_server.domain.Audio;
import com.houseofdolswe.HouserOfDolswe_server.domain.User;
import com.houseofdolswe.HouserOfDolswe_server.domain.UserAudioLike;
import com.houseofdolswe.HouserOfDolswe_server.repository.AudioRepository;
import com.houseofdolswe.HouserOfDolswe_server.repository.UserAudioLikeRepository;
import com.houseofdolswe.HouserOfDolswe_server.repository.UserRepository;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.PostLikeResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AudioCommandServiceImpl implements AudioCommandService {

	private final AudioRepository audioRepository;
	private final UserRepository userRepository;
	private final UserAudioLikeRepository likeRepository;

	@Override
	public PostLikeResponseDTO postLike(Long userId, Long audioId) {
		User user = userRepository.findByUserId(userId)
			.orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

		Audio audio = audioRepository.findByAudioId(audioId)
			.orElseThrow(() -> new AudioHandler(ErrorStatus.AUDIO_NOT_FOUND));

		UserAudioLike like = UserAudioLike.of(user, audio);
		likeRepository.save(like);

		return new PostLikeResponseDTO("completed", like.getUserAudioLikeId());
	}
}
