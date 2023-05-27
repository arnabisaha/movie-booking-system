package com.demo.moviebookingsystem.controller;

import com.demo.moviebookingsystem.models.ExceptionMessageModel;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GenericRestControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionMessageModel> handleNotFoundException(Exception e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(ExceptionMessageModel.builder()
                .message(e.getMessage())
                .cause(httpStatus.getReasonPhrase())
                .build(), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessageModel> handleGenericException(Exception e) {
        log.error("Internal Server error: {}", e.toString());
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(ExceptionMessageModel.builder()
                .message("Internal Server Error occurred! Sorry for inconveniences. Please contact support...")
                .cause(httpStatus.getReasonPhrase())
                .build(), httpStatus);
    }
}
