package com.dss.service;

import com.dss.model.LoginRequestModel;

public interface LoginService {

    boolean validate(LoginRequestModel requestModel);
}
