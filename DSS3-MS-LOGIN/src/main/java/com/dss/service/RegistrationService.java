package com.dss.service;



import com.dss.model.User;
import com.dss.model.UserSearchModel;

import java.util.ArrayList;
import java.util.List;

public interface RegistrationService {
    User save(User user);
    User validate(String emailId, String password);

    List<User> findAllUsers(UserSearchModel searchModel);
}
