package com.chuong.app.service;

import lombok.RequiredArgsConstructor;
import com.chuong.app.dto.response.UserResponse;
import com.chuong.app.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;


    public UserResponse getUserDetail(Long id) {
        var user = userRepository.findById(id);
        return user.map(value -> UserResponse.builder().userId(value.getUserId()).name(value.getName()).build()).orElse(null);
    }
}
