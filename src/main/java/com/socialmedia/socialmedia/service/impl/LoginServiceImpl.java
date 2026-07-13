package com.socialmedia.socialmedia.service.impl;

import com.socialmedia.socialmedia.dto.AuthRequestDTO;
import com.socialmedia.socialmedia.security.CustomUserDetailsService;
import com.socialmedia.socialmedia.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public String login(AuthRequestDTO authRequestDTO) {
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
        return jwtService.generateToken(principal);
    }
}
