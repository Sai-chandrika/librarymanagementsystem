package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.GenericResponse;
import com.example.librarymanagementsystem.dto.LibraryRequestDto;
import com.example.librarymanagementsystem.entity.Library;
import com.example.librarymanagementsystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 11/01/24
 * @Time ➤➤➤ 11:44 am
 * @Project ➤➤➤ librarymanagementsystem
 */
@RestController
@RequestMapping("api/v1/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;


    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public GenericResponse saveLibrary(@RequestBody LibraryRequestDto request) {
        return libraryService.saveLibrary(request);
    }
    }
