package com.dss.service;

import com.dss.exception.NoRegisteredAccountException;
import com.dss.model.User;
import com.dss.repository.AdminRepository;
import com.dss.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class DecryptServiceImpl implements DecryptService {

    @Autowired
    AdminRepository registrationRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public User decryptPass(User user){
        String hashedPassword = getDecryptedPass(user.getPassword());
        user.setPassword(hashedPassword);
        return user;
    }

    public User validateUser(String emailId, String password){
            String hashedPassword = getDecryptedPass(password);
            User user = findByEmailIdAndPassword(emailId,hashedPassword);
            if(user!=null){
                String token = jwtUtil.generateToken(emailId);
                user.setToken(token);
                return user;
            }else throw new NoRegisteredAccountException("No Registered Account!");
    }

    public User findByEmailIdAndPassword(String username, String password){
        return registrationRepository.findByEmailIdAndPassword(username,password);
    }

    private String getDecryptedPass(String password){
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            byte[] passwordInBytes = password.getBytes();
            messageDigest.update(passwordInBytes);
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for(byte byteVar : resultByteArray) {
                String formattedString = String.format("%02x", byteVar);
                stringBuilder.append(formattedString);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm....");
            throw new RuntimeException(e);
        }
    }
}
