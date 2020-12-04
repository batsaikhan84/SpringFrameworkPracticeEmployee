package com.example.demo.exeception;

import com.example.demo.exception.EmployeeNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EmployeeNotFoundException.class})
    protected ResponseEntity<Object> handlerNotFound(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Customer not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
