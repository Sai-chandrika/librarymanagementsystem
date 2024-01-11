package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.AppuserDto;
import com.example.librarymanagementsystem.dto.GenericResponse;
import com.example.librarymanagementsystem.dto.LoginDto;
import com.example.librarymanagementsystem.service.AppUserService;
import com.nimbusds.jose.JOSEException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author chandrika.g
 * user
 * @ProjectName library-management-system
 * @since 09-01-2024
 */
@RestController
@RequestMapping("api/v1/appuser")
public class UserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/save")
    public GenericResponse save(@RequestBody AppuserDto request){
        return appUserService.saveAppUser(request);
    }

    @PutMapping("/update")
    @PreAuthorize(("hasAnyAuthority('ADMIN','USER')"))
    public GenericResponse update(@RequestBody AppuserDto request) {
        return appUserService.update(request);
    }

    @PutMapping("/delete/{id}")
    public GenericResponse delete(@PathVariable String id) {
        return appUserService.delete(id);
    }

    @GetMapping("/get-all")
    public GenericResponse getAll() {
        return appUserService.getAll();
    }

    @PostMapping("/sign-in")
    public GenericResponse signIn(LoginDto request) throws JOSEException {
        return appUserService.signIn(request);
    }

    @GetMapping("/get-all-admin")
    public GenericResponse getAllAdmin(){
        return appUserService.getAllAdmin();
    }
    }
