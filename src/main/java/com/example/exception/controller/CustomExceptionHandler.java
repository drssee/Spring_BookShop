package com.example.exception.controller;

import com.example.exception.IllegalUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@RequestMapping("/errors")
@Slf4j
public class CustomExceptionHandler {

    //IllegalStateException, BAD_REQUEST 400
    //IllegalUserException, UNAUTHORIZED 401
    //NoHandlerFoundException 404
    //MethodNotAllowedException, METHOD_NOT_ALLOWED 405
    //RuntimeException, INTERNAL_SERVER_ERROR 500
    //Exception 500

    @ExceptionHandler(IllegalStateException.class)
    @GetMapping("/error400")
    public String error400(IllegalStateException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return "errors/error400";
    }

    @ExceptionHandler(IllegalUserException.class)
    @GetMapping("/error401")
    public String error401(IllegalUserException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return "errors/error401";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @GetMapping("/error404")
    public String error404(){
        return "errors/error404";
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @GetMapping("/error405")
    public String error405(MethodNotAllowedException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return "errors/error405";
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    @GetMapping("/error500")
    public String error500(Exception e){
        if(e.getClass().equals(ResponseStatusException.class)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        log.error(e.getMessage());
        e.printStackTrace();
        return "errors/error500";
    }
}
