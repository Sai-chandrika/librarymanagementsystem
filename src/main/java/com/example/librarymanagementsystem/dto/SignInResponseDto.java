package com.example.librarymanagementsystem.dto;

import com.example.librarymanagementsystem.entity.AppUser;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 11/01/24
 * @Time ➤➤➤ 10:15 am
 * @Project ➤➤➤ librarymanagementsystem
 */
@Getter
@Setter
public class SignInResponseDto {

    private String id;
    private String email;
    private String roleType;
    private String name;
    private String token;
    public SignInResponseDto(AppUser appUser){
        this.id =appUser.getId();
        this.name = appUser.getName();
        this.email = appUser.getEmail();
        this.roleType = appUser.getRoleType().name();
    }

}
