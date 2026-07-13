package com.socialmedia.socialmedia.repository;

import com.socialmedia.socialmedia.model.RefreshToken;
import com.socialmedia.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);
}
