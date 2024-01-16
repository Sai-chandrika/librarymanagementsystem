package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.book.BookRequest;
import com.example.librarymanagementsystem.dto.GenericResponse;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 12/01/24
 * @Time ➤➤➤ 11:37 am
 * @Project ➤➤➤ librarymanagementsystem
 */
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public GenericResponse saveBook(@RequestBody BookRequest request) {
        return bookService.saveBook(request);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public GenericResponse update(@RequestBody BookRequest request) {
        return bookService.update(request);
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public GenericResponse delete(@PathVariable String id){
        return bookService.delete(id);
    }


    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public GenericResponse getAll(){
        return bookService.getAll();
    }
}
