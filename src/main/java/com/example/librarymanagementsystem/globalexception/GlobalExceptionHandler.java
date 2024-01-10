package com.example.librarymanagementsystem.globalexception;

import com.example.librarymanagementsystem.customexception.*;
import com.example.librarymanagementsystem.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLTimeoutException;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 09/01/24
 * @Time ➤➤➤ 5:17 pm
 * @Project ➤➤➤ librarymanagementsystem
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<GenericResponse> handleGlobalException(Exception ex, WebRequest request){
        if(ex instanceof RuntimeException){
            return composeRunTimeException(ex, request);
        } else if (ex instanceof SQLTimeoutException) {
            return composeTimeOutException(ex, request);
        }
        else return composeGenericException(ex, request);
    }

    private ResponseEntity<GenericResponse> composeTimeOutException(Exception ex,WebRequest request){
        return new ResponseEntity<>(composeGenericResponse(ex.getMessage(), 502,request.getDescription(true)), HttpStatus.GATEWAY_TIMEOUT);
    }

    private ResponseEntity<GenericResponse> composeRunTimeException(Exception ex,WebRequest request){
        ResponseEntity<GenericResponse>  response = null;
        if (ex instanceof NullPointerException || ex instanceof ClassNotFoundException){
            response = new ResponseEntity<>(composeGenericResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(),request.getDescription(true)),HttpStatus.BAD_REQUEST);
        } else if (ex instanceof InvalidDataException){
            response = new ResponseEntity<>(composeGenericResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),request.getDescription(true)),HttpStatus.NOT_FOUND);
        }else if (ex instanceof NotFoundException || ex instanceof DataMisMatchException){
            response = new ResponseEntity<>(composeGenericResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(),request.getDescription(true)),HttpStatus.NOT_FOUND);
        }else if(ex instanceof AlreadyExistsException || ex instanceof AccessDeniedException){
            response = new ResponseEntity<>(composeGenericResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(), request.getDescription(true)),HttpStatus.NOT_ACCEPTABLE);
        }
        return response;
    }

    private ResponseEntity<GenericResponse> composeGenericException(Exception ex, WebRequest request){
        return new ResponseEntity<>(composeGenericResponse(ex.getMessage(),HttpStatus.UNAUTHORIZED.value(), request.getDescription(true)),HttpStatus.UNAUTHORIZED);
    }
    public GenericResponse composeGenericResponse(String message, int errorCode, Object payload){
        return new GenericResponse(message,errorCode,payload);
    }

}

