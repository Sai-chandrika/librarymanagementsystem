package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.constant.RoleType;
import com.example.librarymanagementsystem.customexception.AccessDeniedException;
import com.example.librarymanagementsystem.customexception.NotFoundException;
import com.example.librarymanagementsystem.dto.book.BookRequest;
import com.example.librarymanagementsystem.dto.book.BookResponseDto;
import com.example.librarymanagementsystem.dto.GenericResponse;
import com.example.librarymanagementsystem.entity.AppUser;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Library;
import com.example.librarymanagementsystem.repo.BookRepo;
import com.example.librarymanagementsystem.repo.LibraryRepo;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 12/01/24
 * @Time ➤➤➤ 11:35 am
 * @Project ➤➤➤ librarymanagementsystem
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private LibraryRepo libraryRepo;
    @Override
    public GenericResponse saveBook(BookRequest request){
        AppUser appUser =(AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!appUser.getRoleType().equals(RoleType.ADMIN))
            throw new AccessDeniedException("Only admin can add books to library");
        Library library = libraryRepo.findByAdminEmail(appUser.getEmail());
        Book book = new Book();
        book.setAuthor(request.getAuthor());
        book.setName(request.getName());
        book.setPrice(request.getPrice());
        book.setLibrary(library);
        bookRepo.save(book);
        return new GenericResponse("Book saved successfully", HttpStatus.OK.value(),response(book));
    }

    @Override
    public GenericResponse update(BookRequest request){
        AppUser appUser = (AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       Book book= bookRepo.findById(request.getId()).orElseThrow(()-> new NotFoundException("Id not found"));
        if (!appUser.getEmail().equals(book.getLibrary().getAdminEmail()))
            throw new AccessDeniedException("Access denied to update book details for another user");
        if (request.getName()!=null) {
            book.setName(request.getName());
        }
        if (request.getAuthor()!=null){
            book.setAuthor(request.getAuthor());
        }
        if (request.getPrice()!=null){
            book.setPrice(request.getPrice());
        }
        bookRepo.save(book);
        return new GenericResponse("Updates successfully", HttpStatus.OK.value(),response(book));
    }


    @Override
    public GenericResponse delete(String id) {
        AppUser appUser = (AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Book book= bookRepo.findById(id).orElseThrow(()-> new NotFoundException("Id not found"));
        if (!appUser.getEmail().equals(book.getLibrary().getAdminEmail()))
            throw new AccessDeniedException("Access denied to delete book details for another user");
        book.setActive(!book.isActive());
        bookRepo.save(book);
        return new GenericResponse("Book deleted successfully", HttpStatus.OK.value(),response(book));
    }

    @Override
    public GenericResponse getAll() {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Book> bookList = bookRepo.findAll().stream().filter(e->Boolean.TRUE.equals(e.isActive()) && e.getLibrary().getAdminEmail().equals(appUser.getEmail())).toList();
        return new GenericResponse(HttpStatus.OK.value(), bookList.stream().map(this::response).toList());
    }

    private BookResponseDto response(Book book){
        BookResponseDto response = new BookResponseDto();
        response.setName(book.getName());
        response.setPrice(book.getPrice());
        response.setPrice(book.getPrice());
        response.setAuthor(book.getAuthor());
        response.setLibraryName(book.getLibrary().getName());
        response.setAdmin(book.getLibrary().getAdminEmail());
        return response;
    }
}
