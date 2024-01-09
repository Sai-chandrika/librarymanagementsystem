package com.example.librarymanagementsystem.entity;

import com.example.librarymanagementsystem.constant.Gender;
import com.example.librarymanagementsystem.constant.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 09/01/24
 * @Time ➤➤➤ 3:54 pm
 * @Project ➤➤➤ librarymanagementsystem
 */

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser extends BaseDoc{

    private String name;

    @Column(unique = true)
    private String userName;

    @Column(unique = true)
    private String contactNumber;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private String  address;

}
