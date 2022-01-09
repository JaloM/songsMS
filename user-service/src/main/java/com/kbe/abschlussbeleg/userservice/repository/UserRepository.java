package com.kbe.abschlussbeleg.userservice.repository;

import com.kbe.abschlussbeleg.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository <User, String> {
    User findByUserId(String userId);
}
