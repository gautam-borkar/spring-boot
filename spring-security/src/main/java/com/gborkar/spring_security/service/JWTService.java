package com.gborkar.spring_security.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.KeyGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTService {
    private String key;

    JWTService() throws NoSuchAlgorithmException {
        this.key = Base64.getEncoder().encodeToString(KeyGenerator.getInstance("HMACSHA256").generateKey().getEncoded());
    }

    public String getKey() throws NoSuchAlgorithmException {
        return key;
    }

    public Algorithm getHashAlgorithm() throws IllegalArgumentException, NoSuchAlgorithmException {
        return Algorithm.HMAC256(getKey());
    }
    
    public String generateToken(UserDetails userDetails) throws NoSuchAlgorithmException {
        return JWT.create().withClaim("ROLES",
                userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withSubject(userDetails.getUsername()).withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .sign(getHashAlgorithm());
    }

    public DecodedJWT validateToken(String jwtToken)
            throws IllegalArgumentException, NoSuchAlgorithmException {
        
        JWTVerifier verifier = JWT.require(getHashAlgorithm()).build();
        DecodedJWT decodedJWT = verifier.verify(jwtToken);
        return decodedJWT;
    }
}
