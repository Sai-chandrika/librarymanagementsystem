package com.example.librarymanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 09/01/24
 * @Time ➤➤➤ 5:24 pm
 * @Project ➤➤➤ librarymanagementsystem
 */

@Getter
@Setter
public class GenericResponse {

    private int code;
    private String message;
    private String status;
    private Object data;

    public GenericResponse(int code, String message, String status){
        this.status = status;
        this.code=code;
        this.message = message;
    }

    public GenericResponse(int code, Object data){
        this.code = code;
        this.data =data;
    }

    public GenericResponse(String message,int code,Object data){
        this.message=message;
        this.code=code;
        this.data=data;
    }

    public GenericResponse(int code, String message, String status, Object data) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
