package com.chuong.app.service;


import lombok.RequiredArgsConstructor;
import com.chuong.app.dto.request.LoginRequest;
import com.chuong.app.dto.request.RegisterRequest;
import com.chuong.app.dto.response.AuthResponse;
import com.chuong.app.entities.User;
import com.chuong.app.entities.UserRole;
import com.chuong.app.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    /*
     * REGISTRATION of NEW USER
     * build user object using request object
     * save user in DB
     * generate JWT/refreshToken and send response
     */
    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .build();
        User savedUser = userRepository.save(user);
        var jwt = jwtService.generateToken(savedUser);
        var refreshToken = refreshTokenService.createRefreshToken(user.getEmail());
        return AuthResponse.builder()
                .token(jwt)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }

    /*
     * LOGIN USER
     * using AuthenticationManager to authenticate user
     * fetch user details
     * generate JWT/refreshToken and send response
     */
    public AuthResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwt = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getEmail());
        return AuthResponse.builder()
                .token(jwt)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }
}
