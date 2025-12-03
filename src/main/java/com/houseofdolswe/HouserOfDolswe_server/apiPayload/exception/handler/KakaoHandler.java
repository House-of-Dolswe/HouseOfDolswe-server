package com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.handler;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.code.BaseErrorCode;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.GeneralException;

public class KakaoHandler extends GeneralException {

	public KakaoHandler(BaseErrorCode errorCode) {
		super(errorCode);
	}
}
