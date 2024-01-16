package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.bookuser.BookUserRequest;
import com.example.librarymanagementsystem.dto.GenericResponse;
import com.example.librarymanagementsystem.service.BookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 13/01/24
 * @Time ➤➤➤ 10:21 am
 * @Project ➤➤➤ librarymanagementsystem
 */

@RestController
@RequestMapping("/api/v1/bookUser")
public class BookUserController {

    @Autowired
    private BookUserService bookUserService;

    public GenericResponse save(@RequestBody BookUserRequest request){
      return bookUserService.save(request);

    }
}
