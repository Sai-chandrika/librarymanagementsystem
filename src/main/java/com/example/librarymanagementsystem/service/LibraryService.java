package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.GenericResponse;
import com.example.librarymanagementsystem.dto.LibraryRequestDto;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 11/01/24
 * @Time ➤➤➤ 11:35 am
 * @Project ➤➤➤ librarymanagementsystem
 */
public interface LibraryService {
    GenericResponse saveLibrary(LibraryRequestDto request);

    GenericResponse deleteLibrary(String id);

    GenericResponse update(LibraryRequestDto request);

    GenericResponse getAll();
}
