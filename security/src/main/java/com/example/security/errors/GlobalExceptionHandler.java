package com.example.security.errors;

import feign.FeignException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public String handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        e.printStackTrace();
        return e.getMessage();
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerStatusException(NullPointerException e, HttpServletResponse response) {
        response.setStatus(400);
        e.printStackTrace();
        return e.getMessage();
    }

}
