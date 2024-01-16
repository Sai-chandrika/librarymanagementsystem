package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.book.BookRequest;
import com.example.librarymanagementsystem.dto.GenericResponse;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 12/01/24
 * @Time ➤➤➤ 11:32 am
 * @Project ➤➤➤ librarymanagementsystem
 */
public interface BookService {


    GenericResponse saveBook(BookRequest request);

    GenericResponse update(BookRequest request);

    GenericResponse delete(String id);

    GenericResponse getAll();
}
