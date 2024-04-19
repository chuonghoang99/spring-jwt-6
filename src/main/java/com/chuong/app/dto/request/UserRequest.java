package com.chuong.app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import com.chuong.app.validate.phone.PhoneNumber;

import java.io.Serializable;


@Getter
public class UserRequest implements Serializable {
    @NotBlank(message = "Khong duoc null")
    private String username;

    @Email(message = "Email khong hop le")
    private String email;

    @PhoneNumber(message = "Phone is invalid.")
    private String phone;

}
