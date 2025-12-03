package com.houseofdolswe.HouserOfDolswe_server.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostLikeResponseDTO(
	String status,
	@JsonProperty("like_id") Long likeId
) {
}