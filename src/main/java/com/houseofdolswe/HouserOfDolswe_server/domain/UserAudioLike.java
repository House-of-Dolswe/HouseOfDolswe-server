package com.houseofdolswe.HouserOfDolswe_server.domain;

import com.houseofdolswe.HouserOfDolswe_server.domain.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserAudioLike extends BaseEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "user_audio_like_id")
	private Long userAudioLikeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "audio_id", nullable = false)
	private Audio audio;

	public static UserAudioLike of(User user, Audio audio) {
		UserAudioLike like = UserAudioLike.builder()
			.user(user)
			.audio(audio)
			.build();
		user.getLikes().add(like);
		audio.getLikes().add(like);
		return like;
	}
}
