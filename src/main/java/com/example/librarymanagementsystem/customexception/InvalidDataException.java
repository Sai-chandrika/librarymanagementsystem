package com.example.librarymanagementsystem.customexception;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 09/01/24
 * @Time ➤➤➤ 4:53 pm
 * @Project ➤➤➤ librarymanagementsystem
 */
public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String message){
        super(message);
    }
}
