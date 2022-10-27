package com.dss.service;

import com.dss.exception.DuplicateUserException;
import com.dss.exception.NullValuesException;
import com.dss.model.User;
import com.dss.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    DecryptService decryptService;

    @Override
    public User save(User user) {
        Optional<User> dbUser = registrationRepository.findByEmailId(user.getEmailId());

        if(dbUser.isPresent()){throw new DuplicateUserException("The account email is already saved!");}else {
            if(user.getFirstName().isEmpty() || user.getLastName().isEmpty()){
                throw new NullValuesException("Please fill out all the values.");
            }
            user = decryptService.decryptPass(user);
            return registrationRepository.save(user);
        }
    }

//    @Override
//    public void delete(int id) {
//        registrationRepository.deleteById(id);
//    }
//
    @Override
    public User validate(String emailId, String password) {
        return decryptService.validateUser(emailId,password);
    }
//
//    @Override
//    public ArrayList<User> findAll() {
//        List<User> userList = registrationRepository.findAll();
//        return new ArrayList<User>(userList);
//    }
//
//    @Override
//    public User findById(int id) {
//        Optional<User> optionalUser = registrationRepository.findById(id);
//            if(optionalUser.isPresent()){
//                return optionalUser.get();
//            }else throw new NoRegisteredAccountException("No Registered Account");
//    }
//
//    @Override
//    public User updateUser(String username, String password) {
//        Optional<User> user = registrationRepository.findByUsername(username);
//        if(user.isPresent()){
//            user.get().setPassword(password);
//            User userModel = decryptService.decryptPass(user.get());
//            return registrationRepository.save(userModel);
//        }else throw new NoRegisteredAccountException("No Registered Account");
//    }


}
