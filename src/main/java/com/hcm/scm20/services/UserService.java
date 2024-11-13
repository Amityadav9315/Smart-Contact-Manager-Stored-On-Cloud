package com.hcm.scm20.services;

import java.util.List;
import java.util.Optional;

import com.hcm.scm20.entities.User;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deletionUser(String id);
    boolean isUserExist(String email);
    boolean isUserExistByEmail(String email);
    List<User> getAllUsers();
   //add more methods
}
