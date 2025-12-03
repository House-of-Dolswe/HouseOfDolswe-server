package com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.handler;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.code.BaseErrorCode;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.GeneralException;

public class UserHandler extends GeneralException {

	public UserHandler(BaseErrorCode errorCode) {
		super(errorCode);
	}
}
