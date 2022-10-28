package com.dss.service;

import com.dss.exception.CustomErrorException;
import com.dss.exception.DuplicateUserException;
import com.dss.exception.NullValuesException;
import com.dss.model.User;
import com.dss.repository.AdminRepository;
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
        boolean hasSpChar = checkForSpChar(user.getFirstName(),user.getLastName());
        boolean hasCombination = validateCombination(user.getPassword());
        boolean phoneNumberInUse = validatePhoneNumber(user.getPhoneNumber());
        if(user.getFirstName().isEmpty() || user.getLastName().isEmpty()){
            throw new NullValuesException("Please fill out all the values.");
        }if(hasSpChar){
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
        List<User> userList = adminRepository.findAll();
        for(User user : userList){
            return user.getPhoneNumber().equals(phoneNumber);
        }return false;
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


}
