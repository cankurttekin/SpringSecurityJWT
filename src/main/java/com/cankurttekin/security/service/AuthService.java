package com.cankurttekin.security.service;

import com.cankurttekin.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    public String login(String username, String password) throws AuthenticationException {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        // REMOVED PASS ENCRYPT FOR EASY TESTING PURPOSES DONT DO THAT IN PRODUCTION
//        if (password == userDetails.getPassword()) {
//            return jwtUtil.generateToken(userDetails);
//        } else {
//            throw new AuthenticationException("Invalid credentials") {};
//        }

        System.out.println("deneme");

        Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                username,
                                password
                                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateToken((UserDetails) authentication.getPrincipal());
        return jwt;

        /*
        if (new BCryptPasswordEncoder().matches(password, userDetails.getPassword())) {
            return jwtUtil.generateToken(userDetails);
        } else {
            throw new AuthenticationException("Invalid credentials") {};
        }

         */
    }
}
