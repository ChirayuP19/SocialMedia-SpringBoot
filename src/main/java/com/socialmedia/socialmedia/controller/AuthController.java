package com.socialmedia.socialmedia.controller;

import com.socialmedia.socialmedia.dto.AuthRequestDTO;
import com.socialmedia.socialmedia.model.RefreshToken;
import com.socialmedia.socialmedia.service.JwtService;
import com.socialmedia.socialmedia.service.RefreshTokenService;
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
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private static final String REFRESH_TOKEN = "refreshToken";
    private static final String ACCESS_TOKEN = "accessToken";

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequestDTO authRequestDTO) {
        log.info("Login controller has been called !");
        Map<String, String> login = loginService.login(authRequestDTO);
        return ResponseEntity.ok(Map.of(
                ACCESS_TOKEN, login.get(ACCESS_TOKEN),
                REFRESH_TOKEN, login.get(REFRESH_TOKEN)
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@RequestBody Map<String, String> request) {
        String refreshToken = request.get(REFRESH_TOKEN);
        log.info("Logout controller has been called !");
        refreshTokenService.logoutByToken(refreshToken);
        return ResponseEntity.ok(Map.of("message", "Logout successfully"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get(REFRESH_TOKEN);
        log.info("Refresh token controller has been called !");
        RefreshToken validatedToken = refreshTokenService.verifyRefreshToken(refreshToken);
        refreshTokenService.rotateRefreshToken(validatedToken);
        String newAccessToken = jwtService.generateToken(validatedToken.getUser());
        log.info("New access token generated for user: {}", validatedToken.getUser().getUsername());
        return ResponseEntity.ok(Map.of(
                ACCESS_TOKEN, newAccessToken,
                REFRESH_TOKEN, validatedToken.getToken()
        ));
    }
}
