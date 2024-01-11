package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.AppuserDto;
import com.example.librarymanagementsystem.dto.GenericResponse;
import com.example.librarymanagementsystem.dto.LoginDto;
import com.nimbusds.jose.JOSEException;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 10/01/24
 * @Time ➤➤➤ 12:20 pm
 * @Project ➤➤➤ librarymanagementsystem
 */
public interface AppUserService {

    GenericResponse saveAppUser(AppuserDto request);

    GenericResponse update(AppuserDto request);



    GenericResponse delete(String id);

    GenericResponse getAll();

    GenericResponse signIn(LoginDto request) throws JOSEException;
    GenericResponse getAllAdmin();
}
