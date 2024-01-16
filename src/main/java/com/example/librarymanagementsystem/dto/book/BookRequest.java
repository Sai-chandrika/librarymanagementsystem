package com.example.librarymanagementsystem.dto.book;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 12/01/24
 * @Time ➤➤➤ 11:27 am
 * @Project ➤➤➤ librarymanagementsystem
 */

@Getter
@Setter
public class BookRequest {

    private String id;
    private String name;
    private String author;
    private Float price;

}
