package com.intelliatech.LibraryManagement.repository;

import com.intelliatech.LibraryManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameOrEmailOrMobileNumber(String username, String email, String mobileNumber);
    User findByMobileNumber(String mobileNumber);
    User findByUserId(long userId);
}
