package com.example.librarymanagementsystem.dto;

import com.example.librarymanagementsystem.constant.Gender;
import com.example.librarymanagementsystem.constant.RoleType;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 11/01/24
 * @Time ➤➤➤ 3:01 pm
 * @Project ➤➤➤ librarymanagementsystem
 */
@Getter
@Setter
public class AppUserResponse {

    private String id;
    private String name;
    private String userName;
    private String contactNumber;
    private String email;
    private Gender gender;
    private RoleType roleType;
    private String  address;
}
