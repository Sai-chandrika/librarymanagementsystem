package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.GenericResponse;
import com.example.librarymanagementsystem.dto.library.LibraryRequestDto;
import com.example.librarymanagementsystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public GenericResponse saveLibrary(@RequestBody LibraryRequestDto request) {
        return libraryService.saveLibrary(request);
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public GenericResponse deleteLibrary(@PathVariable String id) {
        return libraryService.deleteLibrary(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public GenericResponse update(@RequestBody LibraryRequestDto request) {
        return libraryService.update(request);
    }

    @GetMapping("/get-all-libraries")
    public GenericResponse getAll(){
        return libraryService.getAll();
    }
}
