package com.houseofdolswe.HouserOfDolswe_server.domain;

import java.util.ArrayList;
import java.util.List;

import com.houseofdolswe.HouserOfDolswe_server.domain.common.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Audio extends BaseEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "audio_id")
	private Long audioId;

	@OneToMany(mappedBy = "audio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserAudioLike> likes = new ArrayList<>();

	// 추후 필요 기능 구현시 추가 예정
}
