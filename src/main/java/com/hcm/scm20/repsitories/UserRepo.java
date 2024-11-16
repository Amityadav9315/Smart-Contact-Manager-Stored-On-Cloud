package com.hcm.scm20.repsitories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcm.scm20.entities.User;
@Repository
public interface UserRepo extends JpaRepository<User, String> {
    //extra method db relatedoperations
    //custom query methods
    //custom finder methods
    Optional<User> findByEmail(String email);
    Optional<User>findByEmailAndPassword(String email,String password);

}
