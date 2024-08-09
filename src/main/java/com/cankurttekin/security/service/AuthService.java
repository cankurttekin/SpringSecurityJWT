package com.cankurttekin.security.service;

import com.cankurttekin.security.jwt.JwtUtil;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;


    public String login(String username, String password) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // REMOVED PASS ENCRYPT FOR EASY TESTING PURPOSES DONT DO THAT IN PRODUCTION
        if (password == userDetails.getPassword()) {
            return jwtUtil.generateToken(userDetails);
        } else {
            throw new AuthenticationException("Invalid credentials") {};
        }


        /*
        if (new BCryptPasswordEncoder().matches(password, userDetails.getPassword())) {
            return jwtUtil.generateToken(userDetails);
        } else {
            throw new AuthenticationException("Invalid credentials") {};
        }

         */
    }
}
