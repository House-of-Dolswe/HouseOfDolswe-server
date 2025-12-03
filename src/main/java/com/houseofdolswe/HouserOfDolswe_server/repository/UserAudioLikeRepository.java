package com.houseofdolswe.HouserOfDolswe_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.houseofdolswe.HouserOfDolswe_server.domain.Audio;
import com.houseofdolswe.HouserOfDolswe_server.domain.User;
import com.houseofdolswe.HouserOfDolswe_server.domain.UserAudioLike;

public interface UserAudioLikeRepository extends JpaRepository<UserAudioLike, Long> {

	Optional<UserAudioLike> existsByUserAndAudio(User user, Audio audio);
}
