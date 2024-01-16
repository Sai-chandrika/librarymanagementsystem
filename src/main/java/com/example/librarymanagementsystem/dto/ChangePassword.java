package com.example.librarymanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 16/01/24
 * @Time ➤➤➤ 10:47 am
 * @Project ➤➤➤ librarymanagementsystem
 */
@Getter
@Setter
public class ChangePassword {

    private String oldPassword;
    private String  newPassword;
    private String confirmPassword;

}
