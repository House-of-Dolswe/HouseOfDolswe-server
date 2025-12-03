package com.houseofdolswe.HouserOfDolswe_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.houseofdolswe.HouserOfDolswe_server.domain.Audio;

public interface AudioRepository extends JpaRepository<Audio, Long> {
	Optional<Audio> findByAudioId(Long audioId);
}
