package com.houseofdolswe.HouserOfDolswe_server.service;

import com.houseofdolswe.HouserOfDolswe_server.web.dto.PostLikeResponseDTO;
import com.houseofdolswe.HouserOfDolswe_server.web.dto.StatusResponseDTO;

public interface AudioCommandService {
	PostLikeResponseDTO postLike(Long userId, Long audioId);

	StatusResponseDTO deleteLike(Long userId, Long audioId);
}
