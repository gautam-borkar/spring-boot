package com.gborkar.spring_security.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;

@Service
public class JWTService {

    public SecretKey getKey() throws NoSuchAlgorithmException {
        return KeyGenerator.getInstance("HmacSHA256").generateKey();
    }
    
    public String generateToken(UserDetails userDetails) throws InvalidKeyException, NoSuchAlgorithmException {
        Map<String, Object> claims = new HashMap<>();

        List<String> roles = new ArrayList<>();
        userDetails.getAuthorities().forEach(role -> roles.add(role.getAuthority()));
        claims.put("ROLES", roles);

        return Jwts.builder().claims(claims).subject(userDetails.getUsername()).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 10)).signWith(getKey()).compact();
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) throws JwtException, IllegalArgumentException, NoSuchAlgorithmException {
        final String userName = extractUsername(jwtToken);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String jwtToken) throws JwtException, IllegalArgumentException, NoSuchAlgorithmException {
        return extractExpiration(jwtToken).before(new Date());
    }

    public String extractUsername(String jwtToken)
            throws JwtException, IllegalArgumentException, NoSuchAlgorithmException {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    private Date extractExpiration(String jwtToken) throws JwtException, IllegalArgumentException, NoSuchAlgorithmException {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    private <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver)
            throws JwtException, IllegalArgumentException, NoSuchAlgorithmException {
        final Claims claims = extractAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken)
            throws JwtException, IllegalArgumentException, NoSuchAlgorithmException {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(jwtToken).getPayload();
    }
}
