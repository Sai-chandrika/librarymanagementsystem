package com.example.librarymanagementsystem.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 09/01/24
 * @Time ➤➤➤ 4:02 pm
 * @Project ➤➤➤ librarymanagementsystem
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookUser extends BaseDoc{

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

    private LocalDate dateOfBorrowed;

    private Boolean isReturned=Boolean.FALSE;

}
