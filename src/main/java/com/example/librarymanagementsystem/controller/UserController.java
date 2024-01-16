package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.ChangePassword;
import com.example.librarymanagementsystem.dto.ForgotPassword;
import com.example.librarymanagementsystem.dto.GenericResponse;
import com.example.librarymanagementsystem.dto.LoginDto;
import com.example.librarymanagementsystem.dto.appuser.AppuserDto;
import com.example.librarymanagementsystem.service.AppUserService;
import com.nimbusds.jose.JOSEException;
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
    public GenericResponse save(@RequestBody AppuserDto request) {
        return appUserService.saveAppUser(request);
    }

    @PutMapping("/update")
    @PreAuthorize(("hasAnyAuthority('ADMIN','USER')"))
    public GenericResponse update(@RequestBody AppuserDto request) {
        return appUserService.update(request);
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize(("hasAnyAuthority('ADMIN','USER')"))
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
    public GenericResponse getAllAdmin() {
        return appUserService.getAllAdmin();
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public GenericResponse changePassword(ChangePassword password) {
        return appUserService.changePassword(password);
    }

    @PostMapping("/forgot-password")
    public GenericResponse forgotPassword(ForgotPassword request) {
        return appUserService.forgotPassword(request);
    }

    @PostMapping("/generate-otp")
    public GenericResponse generateOtp(ForgotPassword forgotPassword){
        return appUserService.generateOtp(forgotPassword);
    }

    @PostMapping("/validate-otp")
    public GenericResponse validateOtp(ForgotPassword forgotPassword){
        return appUserService.validateOtp(forgotPassword);
    }
}
