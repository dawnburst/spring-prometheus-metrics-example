package com.dawn.springbootmatricsdemo.rest.error;

import com.dawn.springbootmatricsdemo.rest.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception e) {

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AgeNotInRangeException.class)
    public ResponseEntity<ErrorDto> handleAgeNotInRangeException(AgeNotInRangeException e) {

        return new ResponseEntity<>(
                new ErrorDto(e.getErrCode(), e.getErrMsg()),
                HttpStatus.BAD_REQUEST);
    }
}
