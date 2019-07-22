package com.app.web.controllers;



import com.app.model.exceptions.Info;
import com.app.model.exceptions.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController
{
    @ExceptionHandler({MyException.class})
    public Info myExceptionHandler(MyException e)
    {
     return Info.builder().exception(e.getExceptionInfo()).build();
    }
}
