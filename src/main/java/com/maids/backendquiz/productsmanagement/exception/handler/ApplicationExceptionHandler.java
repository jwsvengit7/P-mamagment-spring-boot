package com.maids.backendquiz.productsmanagement.exception.handler;

import com.maids.backendquiz.productsmanagement.domain.model.response.APIResponse;
import com.maids.backendquiz.productsmanagement.domain.model.response.DataResponse;
import com.maids.backendquiz.productsmanagement.exception.SecurityAppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<DataResponse>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage()   ;
        DataResponse errorResponse = new DataResponse();
        errorResponse.setMessage("Validation Error: " + errors);
        APIResponse<DataResponse> apiResponse = new APIResponse<>(errorResponse, HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(SecurityAppException.class)
    public ResponseEntity<APIResponse<DataResponse>> handleSecurityAppException(SecurityAppException e) {
        DataResponse customExceptionResponse = new DataResponse();
        customExceptionResponse.setMessage(e.getMessage());
        customExceptionResponse.setLocalDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        APIResponse<DataResponse> apiResponse = new APIResponse<>(customExceptionResponse, HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }
}