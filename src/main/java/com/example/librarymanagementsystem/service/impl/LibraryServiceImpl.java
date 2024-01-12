package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.constant.RoleType;
import com.example.librarymanagementsystem.customexception.*;
import com.example.librarymanagementsystem.dto.GenericResponse;
import com.example.librarymanagementsystem.dto.LibraryRequestDto;
import com.example.librarymanagementsystem.dto.LibraryResponseDto;
import com.example.librarymanagementsystem.entity.AppUser;
import com.example.librarymanagementsystem.entity.Library;
import com.example.librarymanagementsystem.repo.AppUserRepo;
import com.example.librarymanagementsystem.repo.LibraryRepo;
import com.example.librarymanagementsystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 11/01/24
 * @Time ➤➤➤ 11:35 am
 * @Project ➤➤➤ librarymanagementsystem
 */

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepo libraryRepo;

    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public GenericResponse saveLibrary(LibraryRequestDto request){

        if (libraryRepo.findByEmail(request.getEmail())!=null)
            throw new AlreadyExistsException("Library with the email already exists");
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!appUser.getRoleType().equals(RoleType.ADMIN))
            throw new InvalidDataException("Only admin can save library");
        if (libraryRepo.findByAdminEmail(appUser.getEmail())!=null)
            throw new DataMisMatchException("Only One Library can saved by admin");
        Library library = new Library();
        library.setName(request.getName());
        library.setLocation(request.getLocation());
        library.setEmail(request.getEmail());
        library.setAdminEmail(appUser.getEmail());
        libraryRepo.save(library);
        return new GenericResponse("Success", HttpStatus.OK.value(),libraryResponse(library));
    }

    private LibraryResponseDto libraryResponse(Library library){
        LibraryResponseDto response = new LibraryResponseDto();
        response.setName(library.getName());
        response.setEmail(library.getEmail());
        response.setLocation(library.getLocation());
        AppUser appUser =  appUserRepo.findByEmail(library.getAdminEmail());
        response.setAdminEmail(appUser.getEmail());
        response.setAdminName(appUser.getUserName());
        response.setAdminContact(appUser.getContactNumber());
        return response;
    }

    @Override
    public GenericResponse deleteLibrary(String id){
        AppUser authenticator = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryRepo.findById(id).orElseThrow(()-> new NotFoundException("Library Not found"));
        if (!library.getAdminEmail().equals(authenticator.getEmail()))
            throw new AccessDeniedException("Access denied for another admin");
        library.setActive(!library.isActive());
        libraryRepo.save(library);
        return new GenericResponse(HttpStatus.OK.value(), " successfully");
    }

    @Override
    public GenericResponse update(LibraryRequestDto request){
        AppUser authenticator = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryRepo.findById(request.getId()).orElseThrow(()-> new NotFoundException("Library Not found"));
        if (!library.getAdminEmail().equals(authenticator.getEmail()))
            throw new AccessDeniedException("Access denied for another admin to update");
        if (request.getName()!=null){
            library.setName(request.getName());
        }
        if (request.getEmail()!=null){
            library.setEmail(request.getEmail());
        }
        if (request.getLocation()!=null){
            library.setLocation(request.getLocation());
        }
        libraryRepo.save(library);
        return new GenericResponse("Library data updated successfully",HttpStatus.OK.value(),libraryResponse(library));
    }


    @Override
    public GenericResponse getAll(){
        List<Library> libraryList = libraryRepo.findAll().stream().filter(e-> Boolean.TRUE.equals(e.isActive())).collect(Collectors.toList());
        return new GenericResponse(HttpStatus.OK.value(),libraryList.stream().map(this::libraryResponse).collect(Collectors.toList()));
    }
}
