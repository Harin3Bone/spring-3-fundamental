package com.tutorial.springfundamental.controller;

import com.tutorial.springfundamental.dto.GenericResponse;
import com.tutorial.springfundamental.exception.InvalidException;
import com.tutorial.springfundamental.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = Exception.class)
    public GenericResponse unexpectedException(Exception e) {
        return new GenericResponse("9999", e.getClass().getSimpleName(), e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse unexpectedException(WebRequest webRequest, MethodArgumentNotValidException e) {
        var res = new LinkedHashMap<String, Object>();
        for (var err : e.getFieldErrors()) {
            res.put(err.getField(), err.getDefaultMessage());
        }

        return new GenericResponse(webRequest.getContextPath(), e.getClass().getSimpleName(), res);
    }

    @ExceptionHandler(value = {InvalidException.class, NotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse invalidException(RuntimeException e) {
        return new GenericResponse("4xx", e.getClass().getSimpleName(), e.getMessage());
    }
}
