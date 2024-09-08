package com.chuong.app.service;

import com.chuong.app.common.base.PageResponse;
import com.chuong.app.dto.response.UserResponse;
import com.chuong.app.entities.User;
import com.chuong.app.exceptions.ResourceNotFoundException;
import com.chuong.app.repositories.UserRepository;
import com.chuong.app.repositories.criteria.UserCriteriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {


    public final UserRepository userRepository;

    public final UserCriteriaRepository userCriteriaRepository;

    public List<UserResponse> getUserList(Pageable pageable) {

        Page<User> users = userRepository.findAll(pageable);


        return users.stream().map(user -> UserResponse.builder().userId(user.getId()).name(user.getName()).build()).toList();
    }

    public PageResponse<?> getUserListBySearch(Pageable pageable, String address, String... search) {
        return userCriteriaRepository.searchUserByCriteria(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toString(), address, search);
    }

    public PageResponse<?> getUserListBySpecification(Pageable pageable,
                                                      String[] user,
                                                      String[] address) {
//         userCriteriaRepository.searchUserByCriteria(pageable,
//                user, address);

//        userRepository

        List<User> users = new ArrayList<>();

        if (user != null && address != null) {

        } else if (user != null) {
            Specification<User> specification = Specification.where(((root,
                                                                      query,
                                                                      criteriaBuilder) -> criteriaBuilder.like(root.get("firstName"), "%T%")));

            users = userRepository.findAll(specification);


        }


        return PageResponse.builder()
                .totalElements(1)
                .totalPages(1)
                .page(pageable.getPageNumber()).size(pageable.getPageSize())
                .content(null).build();


    }


    public Optional<UserResponse> getUserDetail(Long id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found")));

        if (user.isPresent()) {
            return user.map(value -> UserResponse.builder().userId(value.getId()).name(value.getName()).build());
        }

        return Optional.empty();
    }

    private PageResponse<?> convertToPageResponse(Page<User> users,
                                                  Pageable pageable) {


        return PageResponse.builder()
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .totalPages(users.getTotalPages())
                .content(new ArrayList<>(users.getContent())).build();
    }


}
