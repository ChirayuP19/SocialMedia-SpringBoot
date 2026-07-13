package com.socialmedia.socialmedia.controller;

import com.socialmedia.socialmedia.dto.AuthRequestDTO;
import com.socialmedia.socialmedia.service.impl.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequestDTO authRequestDTO) {
        log.info("Login controller has been called !");
        String token = loginService.login(authRequestDTO);
        return ResponseEntity.ok(Map.of("Token", token));
    }
}
