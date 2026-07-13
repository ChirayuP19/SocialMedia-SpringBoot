package com.socialmedia.socialmedia.service.impl;

import com.socialmedia.socialmedia.exception.ResourceNotFoundException;
import com.socialmedia.socialmedia.model.RefreshToken;
import com.socialmedia.socialmedia.model.User;
import com.socialmedia.socialmedia.repository.RefreshTokenRepository;
import com.socialmedia.socialmedia.service.RefreshTokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(Instant.now().plusSeconds(refreshExpiration))
                .revoked(false)
                .build();
        log.info("Creating refresh token for user: {}", user.getUsername());
        return repository.save(refreshToken);
    }

    @Transactional
    public RefreshToken rotateRefreshToken(RefreshToken oldRefreshToken) {
        oldRefreshToken.setRevoked(true);
        repository.save(oldRefreshToken);
        log.info("Rotating refresh token for user: {}", oldRefreshToken.getUser().getUsername());
        return createRefreshToken(oldRefreshToken.getUser());
    }

    public RefreshToken verifyRefreshToken(String token) {
        RefreshToken refreshToken = repository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));

        if (refreshToken.isRevoked()) {
            log.warn("Refresh token is revoked for user: {}", refreshToken.getUser().getUsername());
            throw new AccessDeniedException("Refresh token has been revoked");
        }

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            repository.delete(refreshToken);
            log.warn("Refresh token has expired for user: {}", refreshToken.getUser().getUsername());
            throw new AccessDeniedException("Refresh token has expired");
        }
        return refreshToken;
    }

    public void revokedAllUserToken(User user) {
        log.info("Revoking all refresh tokens for user: {}", user.getUsername());
        repository.deleteByUser(user);
    }

    @Transactional
    public void logoutByToken(String token) {
        repository.findByToken(token).ifPresent(
                rf -> revokedAllUserToken(rf.getUser())
        );
    }

}
