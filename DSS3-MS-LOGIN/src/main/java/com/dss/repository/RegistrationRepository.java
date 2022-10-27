package com.dss.repository;


import com.dss.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RegistrationRepository extends JpaRepository<User, Integer> {
    User save(User user);
    void deleteById(int id);
   User findByEmailIdAndPassword(String username,String password);

    Optional<User> findByEmailId(String username);
}
