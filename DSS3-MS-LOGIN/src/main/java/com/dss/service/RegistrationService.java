package com.dss.service;



import com.dss.model.User;

import java.util.ArrayList;

public interface RegistrationService {

    User save(User user);
   // void delete(int id);
    User validate(String emailId, String password);

    //ArrayList<User> findAll();

    //User findById(int id);

    // updateUser(String email ,String password);
}
