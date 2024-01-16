package com.example.librarymanagementsystem.dto.appuser;

import com.example.librarymanagementsystem.constant.Gender;
import com.example.librarymanagementsystem.constant.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 10/01/24
 * @Time ➤➤➤ 12:21 pm
 * @Project ➤➤➤ librarymanagementsystem
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppuserDto {

    private String id;
    private String name;
    private String userName;
    private String contactNumber;
    private String email;
    private String password;
    private Gender gender;
    private RoleType roleType;
    private String  address;

}
