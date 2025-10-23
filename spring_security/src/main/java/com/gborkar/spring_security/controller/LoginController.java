package com.gborkar.spring_security.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gborkar.spring_security.domain.AppUser;
import com.gborkar.spring_security.service.LoginService;

import io.jsonwebtoken.security.InvalidKeyException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping(path = "/csrf-token")
    public ResponseEntity<CsrfToken> getCSRFToken(HttpServletRequest req) {
        CsrfToken csrfToken = (CsrfToken) req.getAttribute("_csrf");
        return ResponseEntity.ok().body(csrfToken);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody AppUser user) throws InvalidKeyException, NoSuchAlgorithmException {
        return ResponseEntity.ok().body(loginService.verifyUser(user));
    }
}
