package com.chuong.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import com.chuong.app.common.response.Message;
import com.chuong.app.common.response.ResponseWrapper;
import com.chuong.app.dto.response.UserResponse;
import com.chuong.app.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@ResponseWrapper
public class UserController {
    private final UserService userService;


//    @GetMapping("/list")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseData<List<UserResponse>> getAllUser() {
//
//
//        return new ResponseData<>(HttpStatus.CREATED.value(), "xx");
//    }
//
//    @PutMapping("/update")
//    public String updateUser(@RequestParam int userId, @RequestBody UserRequest user) {
//        return "Toi da update thanh cong";
//
//    }


    @GetMapping()
    @Operation(summary = "Get user detail by id")
    @Message(value = "xxxxx")
    public UserResponse getUserDetail(@RequestParam long id) {
        return userService.getUserDetail(id);
    }

    @GetMapping("/list")
    @Operation(summary = "Get user list")
    public List<UserResponse> getUserList(@RequestParam(defaultValue = "1") int pageIndex ,
                                          @RequestParam(defaultValue = "10") int pageSize) {
        return  userService.getUserList(pageIndex, pageSize );
    }
}
