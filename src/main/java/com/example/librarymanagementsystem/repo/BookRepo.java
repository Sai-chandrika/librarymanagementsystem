package com.example.librarymanagementsystem.repo;

import com.example.librarymanagementsystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 12/01/24
 * @Time ➤➤➤ 11:30 am
 * @Project ➤➤➤ librarymanagementsystem
 */
@Repository
public interface BookRepo extends JpaRepository<Book,String> {



}
