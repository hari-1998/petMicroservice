package com.data.pet.exception;

import com.data.pet.constant.ErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


@ControllerAdvice
public class ControllerAdvisor{

    ErrorObject errorObj;
    ArrayList<ErrorObject> errors;

    @ExceptionHandler
    ResponseEntity<Object> showCustomErrorMessage(Exception exception){

        //Default value taken as 500 & Internal Server Error
        HttpStatus httpStatus=  HttpStatus.INTERNAL_SERVER_ERROR;
        String exceptionMessage= ErrorEnum.ERROR500.getMessage();


        if(exception instanceof HttpRequestMethodNotSupportedException){

            httpStatus=HttpStatus.METHOD_NOT_ALLOWED;
            exceptionMessage=ErrorEnum.ERROR405.getMessage();;
        }
        else if(exception instanceof MissingServletRequestParameterException){

            httpStatus=HttpStatus.BAD_REQUEST;
            exceptionMessage=ErrorEnum.ERROR400.getMessage();
        }
        else if (exception instanceof  MissingRequestHeaderException){
            httpStatus=HttpStatus.UNAUTHORIZED;
            exceptionMessage=ErrorEnum.ERROR401.getMessage();
        }
        else if (exception instanceof ResponseStatusException){
            ResponseStatusException responseStatusException= (ResponseStatusException) exception;
            httpStatus=  responseStatusException.getStatus();
            exceptionMessage=responseStatusException.getReason();
        }

        errors=new ArrayList<>();
        errorObj=new ErrorObject( httpStatus.toString(),exceptionMessage,"ERROR");

        Map<String, Object> body = new LinkedHashMap<>();
        errors.add(errorObj);
        body.put("messages", errors);

        return new ResponseEntity<>(body,httpStatus);
    }

}
