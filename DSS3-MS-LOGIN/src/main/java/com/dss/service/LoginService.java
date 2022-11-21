package com.dss.service;

import com.dss.model.LoginRequestModel;

public interface LoginService {

    String validate(LoginRequestModel requestModel);
}
