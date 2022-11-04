package com.dss.repository;


import com.dss.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    User findByEmailIdAndPassword(String username,String password);
    Optional<User> findByEmailId(String username);

    User findByPhoneNumber(String phoneNumber);
}
