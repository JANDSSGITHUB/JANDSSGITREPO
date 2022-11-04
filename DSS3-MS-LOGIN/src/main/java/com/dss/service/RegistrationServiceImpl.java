package com.dss.service;

import com.dss.exception.CustomErrorException;
import com.dss.exception.DuplicateUserException;
import com.dss.exception.NullValuesException;
import com.dss.model.User;
import com.dss.model.UserSearchModel;
import com.dss.repository.AdminRepository;
import com.dss.repository.AdminRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    DecryptService decryptService;

    @Override
    public User save(User user) {
        Optional<User> dbUser = adminRepository.findByEmailId(user.getEmailId());

        if(dbUser.isPresent()){
            throw new DuplicateUserException("Email has already been used.");
        }else {
            validate(user);
            user = decryptService.decryptPass(user);
            return adminRepository.save(user);
        }
    }

    private void validate(User user){

        if(user.getEmailId() == null||user.getFirstName() == null || user.getLastName() == null
        ||user.getPhoneNumber() ==null || user.getPassword() ==null){
            throw new NullValuesException("Please fill out all the values.");
        }
        boolean hasSpChar = checkForSpChar(user.getFirstName(),user.getLastName());
        boolean hasCombination = validateCombination(user.getPassword());
        boolean phoneNumberInUse = validatePhoneNumber(user.getPhoneNumber());
        if(hasSpChar){
            throw new CustomErrorException("Special characters/numbers are invalid for First Name & Last Name");
        }
        if(!hasCombination){
            throw new CustomErrorException("Password should be a combination of at least an uppercase alphabet, lowercase  alphabets, a digit, and a special character.");
        }
        if(phoneNumberInUse){
            throw new CustomErrorException("Phone number has already been used");
        }
    }

    private boolean validatePhoneNumber(String phoneNumber){
        User user = adminRepository.findByPhoneNumber(phoneNumber);
        return user != null;
    }

    private boolean checkForSpChar(String fName, String lName){
        return !fName.matches("[a-zA-Z]+") || !lName.matches("[a-zA-Z]+");
    }

    private boolean validateCombination(String password){
        return Pattern.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_\\+\\-=\\[\\]{}|;':\",.\\/<>?~`])(?=.*\\d)[A-Za-z].*", password);
    }



    @Override
    public User validate(String emailId, String password) {
        return decryptService.validateUser(emailId,password);
    }

    @Override
    public List<User> findAllUsers(UserSearchModel searchModel) {
        User user = new User(searchModel.getEmail(),searchModel.getFirstName(),searchModel.getLastName(),searchModel.getPhoneNumber());
        return adminRepository.findAll(AdminRepositoryImpl.createCriteria(user));
    }


}
