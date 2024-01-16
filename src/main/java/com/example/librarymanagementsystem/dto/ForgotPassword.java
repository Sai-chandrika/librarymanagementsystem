package com.example.librarymanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 16/01/24
 * @Time ➤➤➤ 11:17 am
 * @Project ➤➤➤ librarymanagementsystem
 */
@Getter
@Setter
public class ForgotPassword {

    private String email;
    private String contactNumber;
    private String userName;
    private String newPassword;
    private String confirmPassword;
    private Integer otp;
}
