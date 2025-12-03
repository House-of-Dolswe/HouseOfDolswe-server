package com.houseofdolswe.HouserOfDolswe_server.service;

import com.houseofdolswe.HouserOfDolswe_server.web.dto.PostLikeResponseDTO;

public interface AudioCommandService {
	PostLikeResponseDTO postLike(Long userId, Long audioId);
}
