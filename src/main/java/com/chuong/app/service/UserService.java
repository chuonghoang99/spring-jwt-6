package com.chuong.app.service;

import com.chuong.app.dto.response.UserResponse;
import com.chuong.app.entities.User;
import com.chuong.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;

    public List<UserResponse> getUserList(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<User> users = userRepository.findAll(pageable);
        return users.stream().map(user -> UserResponse.builder().userId(user.getId()).name(user.getName()).build()).toList();
    }


    public UserResponse getUserDetail(Long id) {
        var user = userRepository.findById(id);
        return user.map(value -> UserResponse.builder().userId(value.getId()).name(value.getName()).build()).orElse(null);
    }
}
