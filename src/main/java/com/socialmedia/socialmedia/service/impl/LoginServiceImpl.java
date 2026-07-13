package com.socialmedia.socialmedia.service.impl;

import com.socialmedia.socialmedia.dto.AuthRequestDTO;
import com.socialmedia.socialmedia.model.RefreshToken;
import com.socialmedia.socialmedia.model.User;
import com.socialmedia.socialmedia.security.CustomUserDetailsService;
import com.socialmedia.socialmedia.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshTokenServiceImpl refreshTokenService;

    @Override
    public HashMap<String,String> login(AuthRequestDTO authRequestDTO) {
        log.info("User {} is trying to login", authRequestDTO.getUsername());
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDTO.getUsername(),
                        authRequestDTO.getPassword()
                )
        );
        UserDetails principal = (UserDetails) authenticate.getPrincipal();
        assert principal != null;
        log.info("User {} has been authenticated successfully", principal.getUsername());
        String generateToken = jwtService.generateToken(principal);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken((User) principal);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessToken", generateToken);
        hashMap.put("refreshToken", refreshToken.getToken());
        return hashMap;
    }
}
