package com.gborkar.spring_security.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gborkar.spring_security.domain.AppUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JWTService jwtService;

    public String verifyUser(AppUser user) throws NoSuchAlgorithmException {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (auth.isAuthenticated()) {
            UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
            return jwtService.generateToken(userDetails);
        }

        return null;
    }
    
}
