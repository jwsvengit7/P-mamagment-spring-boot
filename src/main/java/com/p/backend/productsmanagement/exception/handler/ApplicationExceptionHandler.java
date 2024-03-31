package com.p.backend.productsmanagement.exception.handler;

import com.p.backend.productsmanagement.domain.model.response.APIResponse;
import com.p.backend.productsmanagement.domain.model.response.DataResponse;
import com.p.backend.productsmanagement.exception.SecurityAppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<DataResponse>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage()   ;
        DataResponse errorResponse = new DataResponse();
        errorResponse.setMessage("Validation Error: " + errors);
        errorResponse.setLocalDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        return new ResponseEntity<>(new APIResponse<>(errorResponse, HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SecurityAppException.class)
    public ResponseEntity<APIResponse<DataResponse>> handleSecurityAppException(SecurityAppException e) {
        DataResponse customExceptionResponse = new DataResponse();
        customExceptionResponse.setMessage(e.getMessage());
        customExceptionResponse.setLocalDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        return new ResponseEntity<>(new APIResponse<>(customExceptionResponse, HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }
}