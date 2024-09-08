package com.chuong.app.controller;

import com.chuong.app.common.base.PageResponse;
import com.chuong.app.common.paging.HandsomePaging;
import com.chuong.app.common.response.Message;
import com.chuong.app.common.response.ResponseWrapper;
import com.chuong.app.dto.response.UserResponse;
import com.chuong.app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@ResponseWrapper
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @GetMapping()
    @Operation(summary = "Get user detail by id")
    @Message(value = "xxxxx")
    public Optional<UserResponse> getUserDetail(@RequestParam long id) {
        log.info("dfdssddsfdss");
        return userService.getUserDetail(id);
    }

    @GetMapping("/list")
    @Operation(summary = "Get user list")
    @HandsomePaging
    public List<UserResponse> getUserList(@ParameterObject Pageable pageable) {
        return userService.getUserList(pageable);
    }


    @GetMapping("/list-search")
    @Operation(summary = "Get user list by search")
    @HandsomePaging
    public PageResponse<?> getUserListByCriteria(@ParameterObject Pageable pageable, @RequestParam(required = false) String address, @RequestParam(required = false) String... search) {
        return userService.getUserListBySearch(pageable, address, search);
    }


    @GetMapping("/list-search-by-specification")
    @Operation(summary = "Get user list by search")
    @HandsomePaging
    public PageResponse<?> getUserListAdvanceSpecification(@ParameterObject Pageable pageable,
                                                           @RequestParam(required = false) String[] user, @RequestParam(required = false) String[] address) {
        return userService.getUserListBySpecification(pageable, user, address);
    }

}
