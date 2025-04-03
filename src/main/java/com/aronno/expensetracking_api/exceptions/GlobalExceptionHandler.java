package com.aronno.expensetracking_api.exceptions;

import com.aronno.expensetracking_api.entity.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(Date.valueOf(java.time.LocalDate.now()));

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorObject> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.CONFLICT.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(Date.valueOf(java.time.LocalDate.now()));

        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage("Invalid argument type: " + ex.getValue());
        errorObject.setTimestamp(Date.valueOf(java.time.LocalDate.now()));

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleException(Exception ex, WebRequest request) {

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(Date.valueOf(java.time.LocalDate.now()));

        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
