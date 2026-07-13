package com.socialmedia.socialmedia.service;


import com.socialmedia.socialmedia.model.RefreshToken;
import com.socialmedia.socialmedia.model.User;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(User user);

    RefreshToken rotateRefreshToken(RefreshToken oldRefreshToken);

    RefreshToken verifyRefreshToken(String token);

    public void revokedAllUserToken(User user);

    public void logoutByToken(String token);
}
