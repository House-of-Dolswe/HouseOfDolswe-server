package com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.handler;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.code.BaseErrorCode;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

	public TempHandler(BaseErrorCode errorCode) {
		super(errorCode);
	}
}