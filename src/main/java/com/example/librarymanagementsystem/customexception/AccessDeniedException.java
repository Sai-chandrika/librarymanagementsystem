package com.example.librarymanagementsystem.customexception;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 09/01/24
 * @Time ➤➤➤ 4:51 pm
 * @Project ➤➤➤ librarymanagementsystem
 */
public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException(String message){
        super(message);
    }

}
