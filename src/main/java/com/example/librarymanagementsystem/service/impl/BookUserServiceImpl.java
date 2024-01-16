package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.customexception.InvalidDataException;
import com.example.librarymanagementsystem.customexception.NotFoundException;
import com.example.librarymanagementsystem.dto.bookuser.BookUserRequest;
import com.example.librarymanagementsystem.dto.GenericResponse;
import com.example.librarymanagementsystem.entity.AppUser;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.BookUser;
import com.example.librarymanagementsystem.repo.AppUserRepo;
import com.example.librarymanagementsystem.repo.BookRepo;
import com.example.librarymanagementsystem.repo.BookUserRepo;
import com.example.librarymanagementsystem.service.BookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 13/01/24
 * @Time ➤➤➤ 10:20 am
 * @Project ➤➤➤ librarymanagementsystem
 */

@Service
public class BookUserServiceImpl implements BookUserService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private BookUserRepo bookUserRepo;
    @Override
    public GenericResponse save(BookUserRequest request) {
        Book book = bookRepo.findById(request.getBookId()).orElseThrow(()-> new NotFoundException("book Not found"));
        if (Boolean.FALSE.equals(book.isActive()))
            throw new InvalidDataException("Book is not available");
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookUser bookUser = bookUserRepo.findByDateOfBorrowedAndBookId(request.getDateOfBorrowed(),book.getId());
        

        return null;
    }
}
