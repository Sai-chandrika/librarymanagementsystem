package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.bookuser.BookUserRequest;
import com.example.librarymanagementsystem.dto.GenericResponse;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 13/01/24
 * @Time ➤➤➤ 10:19 am
 * @Project ➤➤➤ librarymanagementsystem
 */

public interface BookUserService {

    GenericResponse save(BookUserRequest request);
}
