package com.task.exception.handler;

import com.task.exception.exceptions.BadRequestException;
import com.task.exception.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Date;
import java.util.Objects;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = IdNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(IdNotFoundException ex) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setDate(new Date());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setDate(new Date());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentException(MethodArgumentNotValidException ex) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());
        exceptionResponse.setDate(new Date());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
