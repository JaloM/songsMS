package com.kbe.abschlussbeleg.userservice.service;

import com.kbe.abschlussbeleg.userservice.model.User;
import com.kbe.abschlussbeleg.userservice.repository.UserRepository;
import com.kbe.abschlussbeleg.userservice.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;


    public Optional<User> getUserById(String userId) {
        return Optional.ofNullable(userRepository.findByUserId(userId));
    }


    public String getUserIdFromToken(String token) {
        try {
            return jwtUtil.extractUsername(token);
        }catch (ExpiredJwtException | HttpClientErrorException e) {
            return e.getMessage();
        }
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = getUserById(s);

        return new org.springframework.security.core.userdetails.User(
                user.get().getUserId(), user.get().getPassword(), new ArrayList<>());
    }
}
