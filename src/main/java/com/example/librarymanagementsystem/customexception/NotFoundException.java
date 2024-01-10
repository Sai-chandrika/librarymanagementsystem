package com.example.librarymanagementsystem.customexception;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 09/01/24
 * @Time ➤➤➤ 4:55 pm
 * @Project ➤➤➤ librarymanagementsystem
 */
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }
}
