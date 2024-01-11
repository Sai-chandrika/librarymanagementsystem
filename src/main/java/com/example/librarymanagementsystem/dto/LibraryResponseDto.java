package com.example.librarymanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 11/01/24
 * @Time ➤➤➤ 11:43 am
 * @Project ➤➤➤ librarymanagementsystem
 */

@Getter
@Setter
public class LibraryResponseDto {

    private String name;
    private String location;
    private String email;
    private String adminName;
    private String adminEmail;
    private String adminContact;
}
