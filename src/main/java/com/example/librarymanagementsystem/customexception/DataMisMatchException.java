package com.example.librarymanagementsystem.customexception;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 09/01/24
 * @Time ➤➤➤ 4:54 pm
 * @Project ➤➤➤ librarymanagementsystem
 */
public class DataMisMatchException extends RuntimeException{

    public DataMisMatchException(String message){
        super(message);
    }
}
