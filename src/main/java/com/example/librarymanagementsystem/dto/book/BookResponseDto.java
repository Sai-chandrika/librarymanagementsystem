package com.example.librarymanagementsystem.dto.book;

import com.example.librarymanagementsystem.entity.Library;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 16/01/24
 * @Time ➤➤➤ 10:17 am
 * @Project ➤➤➤ librarymanagementsystem
 */
@Getter
@Setter
public class BookResponseDto {

    private String name;
    private String author;
    private Float price;
    private String libraryName;
    private String libraryLocation;
    private String admin;
}
