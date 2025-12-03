package com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.handler;

import com.houseofdolswe.HouserOfDolswe_server.apiPayload.code.BaseErrorCode;
import com.houseofdolswe.HouserOfDolswe_server.apiPayload.exception.GeneralException;

public class OnboardingHandler extends GeneralException {

	public OnboardingHandler(BaseErrorCode errorCode) {
		super(errorCode);
	}
}
