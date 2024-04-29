package com.shelfscribe.backendbookshelfregistry.config;

import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.exceptions.CustomException;
import com.shelfscribe.backendbookshelfregistry.exceptions.NoProductFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomException(CustomException ex){
        ApiResponse<Object> response = new ApiResponse<>(
                false,
                ex.getMessage(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<ApiResponse<Object>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoProductFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoProductFoundException(){
        ApiResponse<Object> response = new ApiResponse<>(
                                                false,
                                                "No Product Found",
                                                "No Product Found",
                                                null
                                                );
        return new ResponseEntity<ApiResponse<Object>>(response, HttpStatus.BAD_REQUEST);
    }

}
