package com.dss.service;



import com.dss.model.User;

import java.util.ArrayList;

public interface RegistrationService {
    User save(User user);
    User validate(String emailId, String password);
}
