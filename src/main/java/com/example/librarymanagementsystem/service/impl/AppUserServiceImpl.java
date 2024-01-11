package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.config.JwtTokenUtils;
import com.example.librarymanagementsystem.constant.RoleType;
import com.example.librarymanagementsystem.customexception.*;
import com.example.librarymanagementsystem.dto.*;
import com.example.librarymanagementsystem.entity.AppUser;
import com.example.librarymanagementsystem.entity.BaseDoc;
import com.example.librarymanagementsystem.repo.AppUserRepo;
import com.example.librarymanagementsystem.service.AppUserService;
import com.example.librarymanagementsystem.utility.GenericAppValidator;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 10/01/24
 * @Time ➤➤➤ 12:20 pm
 * @Project ➤➤➤ librarymanagementsystem
 */

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${appUser.login.type}")
    private String loginTypeValue;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public GenericResponse saveAppUser(AppuserDto request){

        if (appUserRepo.findByEmail(request.getEmail())!=null)
            throw  new AlreadyExistsException("Email already exists");
        if (appUserRepo.findByUserName(request.getUserName())!=null)
            throw new AlreadyExistsException(" user name already exists");
        if (appUserRepo.findByContactNumber(request.getContactNumber())!=null)
            throw new AlreadyExistsException("Contact Number");
        validateAppUser(request);
        AppUser appUser = new AppUser();
        appUser.setName(request.getName());
        appUser.setGender(request.getGender());
        appUser.setEmail(request.getEmail());
        appUser.setUserName(request.getUserName());
        appUser.setRoleType(request.getRoleType());
        appUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        appUser.setContactNumber(request.getContactNumber());
        appUser.setAddress(request.getAddress());
        appUserRepo.save(appUser);
        return new GenericResponse("AppUser saved successfully", HttpStatus.OK.value(),entityToDto(appUser));
    }

    private void validateAppUser(AppuserDto request){
        if (Boolean.TRUE.equals(!GenericAppValidator.isValidEmail(request.getEmail()))){
            throw new DataMisMatchException("InValidEmail format");
        }
        if (Boolean.TRUE.equals(!GenericAppValidator.isValidPassword(request.getPassword())))
            throw new DataMisMatchException("Password invalid format");
        if (Boolean.TRUE.equals(!GenericAppValidator.isValidContactNumber(request.getContactNumber())))
            throw new DataMisMatchException("Invalid contact Number");
    }

    @Override
    public GenericResponse update(AppuserDto request){
        AppUser authenticator = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AppUser appuser = appUserRepo.findById(request.getId()).orElseThrow(()-> new NotFoundException("user not found"));
        if (!authenticator.getId().equals(appuser.getId())){
            throw new InvalidDataException("access denied to update other user");
        }
        validateAppUser(request);
        if (request.getUserName()!=null && !request.getUserName().equals(appuser.getUserName())){
            appuser.setUserName(request.getUserName());
        }
        if (request.getEmail()!=null && !request.getEmail().equals(appuser.getEmail())){
            appuser.setEmail(request.getEmail());
        }
        if (request.getContactNumber()!=null && !request.getContactNumber().equals(appuser.getContactNumber())){
            appuser.setContactNumber(request.getContactNumber());
        }
        if (request.getGender()!=null && !request.getGender().equals(appuser.getGender())) {
            appuser.setGender(request.getGender());
        }
        if (request.getPassword()!=null && !bCryptPasswordEncoder.encode(request.getPassword()).matches(appuser.getPassword())) {
            appuser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        }
        if (request.getAddress()!=null && !request.getAddress().equals(appuser.getAddress())) {
            appuser.setAddress(request.getAddress());
        }
        appUserRepo.save(appuser);
        return new GenericResponse("AppUser updated successfully", HttpStatus.OK.value(),entityToDto(appuser));
    }

    @Override
    public GenericResponse delete(String id){
        AppUser appuser = appUserRepo.findById(id).orElseThrow(()-> new NotFoundException("user not found"));
        if (Boolean.FALSE.equals(appuser.isActive())){
            throw new  DataMisMatchException("User not activated");
        }
        if (Boolean.TRUE.equals(appuser.isActive())){
            appuser.setActive(Boolean.FALSE);
        }
        appUserRepo.save(appuser);
        return new GenericResponse(HttpStatus.OK.value(),"Appuser deleted successfully");
    }

    @Override
    public GenericResponse getAll(){
        List<AppUser> appUsers = appUserRepo.findAll().stream().filter(BaseDoc::isActive).collect(Collectors.toList());
        return new GenericResponse(HttpStatus.OK.value(), appUsers.stream().map(this::entityToDto).collect(Collectors.toList()));
    }

    private AppUser appUserData(LoginDto request){
        AppUser appUser;
        String errorMessage = "Requested credentials not found as per application properties";
        switch (loginTypeValue){
            case "EMAIL" ->{
                if (Boolean.TRUE.equals(GenericAppValidator.isNull(request.getEmail())))
                    throw new NotFoundException(errorMessage);
                appUser = appUserRepo.findByEmail(request.getEmail());
            }
            case "USERNAME" ->{
                if (Boolean.TRUE.equals(GenericAppValidator.isNull(request.getUserName())))
                    throw new NotFoundException(errorMessage);
                appUser = appUserRepo.findByUserName(request.getUserName());
            }
            case "CONTACTNUMBER" ->{
                if (Boolean.TRUE.equals(GenericAppValidator.isNull(request.getContactNumber())))
                    throw new NotFoundException(errorMessage);
                appUser=appUserRepo.findByContactNumber(request.getContactNumber());
            }
            default -> throw new InvalidDataException("invalid data defining at application properties");
        }
        return appUser;
    }


    @Override
    public GenericResponse signIn(LoginDto request) throws JOSEException {
        AppUser appUser = appUserData(request);
        if (appUser==null)
            throw new DataMisMatchException("No app user found with given login type details");
        if (Boolean.FALSE.equals(appUser.isActive())) throw new AccessDeniedException("App user is disabled");
        String password =request.getPassword();
        if (!bCryptPasswordEncoder.matches(password,appUser.getPassword()))
            throw new DataMisMatchException("Password is wrong");
        SignInResponseDto response = new SignInResponseDto(appUser);
        response.setToken(jwtTokenUtils.getToken(appUser));
        return new GenericResponse("Successfully sign in..", HttpStatus.OK.value(), response);
    }

    @Override
    public GenericResponse getAllAdmin(){
        List<AppUser> adminList = appUserRepo.findAll().stream().filter(a->a.getRoleType().equals(RoleType.ADMIN) && Boolean.TRUE.equals(a.isActive())).collect(Collectors.toList());
        return new GenericResponse(HttpStatus.OK.value(),adminList.stream().map(this::entityToDto).collect(Collectors.toList()));
    }

    private AppUserResponse entityToDto(AppUser appUser){
        AppUserResponse response = new AppUserResponse();
        response.setId(appUser.getId());
        response.setName(appUser.getName());
        response.setUserName(appUser.getUserName());
        response.setGender(appUser.getGender());
        response.setContactNumber(appUser.getContactNumber());
        response.setAddress(appUser.getAddress());
        response.setEmail(appUser.getEmail());
        return response;
    }
}