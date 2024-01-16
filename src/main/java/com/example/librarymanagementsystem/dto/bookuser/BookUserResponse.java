package com.example.librarymanagementsystem.dto.bookuser;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 13/01/24
 * @Time ➤➤➤ 10:14 am
 * @Project ➤➤➤ librarymanagementsystem
 */

@Getter
@Setter
public class BookUserResponse {

    private String bookName;
    private String userName;
    private String borrowedData;
    private String libraryName;
    private String location;
    private String author;
}
