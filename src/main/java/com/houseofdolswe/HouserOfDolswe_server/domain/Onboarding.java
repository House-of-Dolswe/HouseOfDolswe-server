package com.houseofdolswe.HouserOfDolswe_server.domain;

import com.houseofdolswe.HouserOfDolswe_server.domain.common.BaseEntity;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.Age;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.Call;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.House;
import com.houseofdolswe.HouserOfDolswe_server.domain.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
public class Onboarding extends BaseEntity {
	// 식별 관계, userId값을 Onboarding의 pk로 사용
	@Id
	private Long userId;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@MapsId                              // <-- 핵심: user의 id를 이 엔티티의 PK로 “맵핑”
	@JoinColumn(name = "user_id")
	private User user;

	@Enumerated(EnumType.STRING)
	@Column(name = "house_type", nullable = false, length = 20)
	private House house;

	// call은 MySQL 예약어이므로 call_type으로 변경
	@Enumerated(EnumType.STRING)
	@Column(name = "call_type", nullable = false, length = 20)
	private Call call;

	@Enumerated(EnumType.STRING)
	@Column(name = "age_range", nullable = false, length = 20)
	private Age age;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Status status = Status.NOT_STARTED;
}