package com.example.librarymanagementsystem.repo;

import com.example.librarymanagementsystem.entity.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 13/01/24
 * @Time ➤➤➤ 10:54 am
 * @Project ➤➤➤ librarymanagementsystem
 */
@Repository
public interface BookUserRepo extends JpaRepository<BookUser,String> {
    BookUser findByDateOfBorrowedAndBookId(LocalDate dateOfBorrowed, String id);
}
