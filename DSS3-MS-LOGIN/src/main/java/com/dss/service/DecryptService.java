package com.dss.service;



import com.dss.model.User;

public interface DecryptService {

    User validateUser(String emailId, String password);
    User decryptPass(User user);

}
