package com.noirix.controller.exception;

import com.noirix.controller.responces.ErrorMessage;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {
    private static final Logger log = Logger.getLogger(DefaultExceptionHandler.class);

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        log.error(e.getMessage(), e);
//        return new ResponseEntity<>(new ErrorMessage(1L, e.getLocalizedMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
        /* Handles all other exceptions. Status code 500. */
        log.error(e.getMessage(), e);
        log.info(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(2L, e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
