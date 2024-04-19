package com.chuong.app.service;


import com.chuong.app.entities.RefreshToken;
import com.chuong.app.entities.User;
import com.chuong.app.exceptions.TokenException;
import com.chuong.app.exceptions.UsernameNotFoundException;
import com.chuong.app.repositories.RefreshTokenRepository;
import com.chuong.app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;


    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken createRefreshToken(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));

        RefreshToken refreshToken = user.getRefreshToken();

        if (refreshToken == null) {
            long refreshTokenValidity = 30 * 1000;
            refreshToken = RefreshToken.builder().refreshToken(UUID.randomUUID().toString()).expirationTime(Instant.now().plusMillis(refreshTokenValidity)).user(user).build();


            // save refresh token
            refreshTokenRepository.save(refreshToken);
        }

        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken) {

        RefreshToken refreshTokenOb = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new TokenException("Refresh token not exists"));

        if (refreshTokenOb.getExpirationTime().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshTokenOb);
            throw new TokenException("Refresh token expired");
        }

        return refreshTokenOb;
    }


}
