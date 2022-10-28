package com.dss.repository;


import com.dss.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdminRepository extends JpaRepository<User, String> {
    User save(User user);
   User findByEmailIdAndPassword(String username,String password);

    Optional<User> findByEmailId(String username);
}
