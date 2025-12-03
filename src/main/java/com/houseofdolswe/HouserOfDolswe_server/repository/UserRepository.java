package com.houseofdolswe.HouserOfDolswe_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.houseofdolswe.HouserOfDolswe_server.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserId(Long userId);
}
