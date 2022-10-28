package com.dss.service;

import com.dss.model.LoginRequestModel;
import com.dss.model.User;
import com.dss.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{


    @Autowired
    DecryptService decryptService;

    @Override
    public boolean validate(LoginRequestModel requestModel) {
        User user = decryptService.validateUser(requestModel.getEmailId(), requestModel.getPassword());
        return user != null;
    }
}
