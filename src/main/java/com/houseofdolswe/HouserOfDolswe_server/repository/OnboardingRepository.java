package com.houseofdolswe.HouserOfDolswe_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.houseofdolswe.HouserOfDolswe_server.domain.Onboarding;
import com.houseofdolswe.HouserOfDolswe_server.domain.User;

public interface OnboardingRepository extends JpaRepository<Onboarding, Long> {

	Optional<Onboarding> findByUser(User user);
}
