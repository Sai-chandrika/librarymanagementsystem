package com.example.librarymanagementsystem.dto.bookuser;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 13/01/24
 * @Time ➤➤➤ 10:12 am
 * @Project ➤➤➤ librarymanagementsystem
 */

@Getter
@Setter
public class BookUserRequest {

    private String bookId;
    private String userId;
    private LocalDate dateOfBorrowed;

}
