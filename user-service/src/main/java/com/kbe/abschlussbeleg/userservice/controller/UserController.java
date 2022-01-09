package com.kbe.abschlussbeleg.userservice.controller;

import com.kbe.abschlussbeleg.userservice.model.User;
import com.kbe.abschlussbeleg.userservice.service.UserService;
import com.kbe.abschlussbeleg.userservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/songsWS-Moroz_KBE/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping(consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> authUser(@RequestBody User user) {
        if(user==null || user.getUserId()==null || user.getPassword()==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> authUser = userService.getUserById(user.getUserId());
        if (authUser.isEmpty() || !authUser.get().getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            final String token = jwtUtil.generateToken(user);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
    }


    @GetMapping(value = "/{token}")
    public String authToken(@PathVariable(value = "token") String token)  {
        try {
            return userService.getUserIdFromToken(token);
        }catch (Exception e) {
            return null;
        }
    }

}
