package com.saisai.web.exception;


import com.saisai.web.util.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(SaisaiException.class)
//    public ResponseEntity<Message> handleSaisaiException(SaisaiException exception) {
//        System.out.println(exception.getMessage());
//        ErrorResponse response = new ErrorResponse(exception.getErrorCode());
//        Message message = new Message(false, response);
//        return new ResponseEntity<>(message, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
//    }
//
//    @ExceptionHandler(ExternalException.class)
//    public ResponseEntity<Message> handleExternalApiServerException(ExternalException exception) {
//        System.out.println(exception.getMessage());
//        ErrorResponse response = new ErrorResponse(exception.getErrorCode());
//        response.setMessage(exception.getMessage());
//        Message message = new Message(false, response);
//        return new ResponseEntity<>(message, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Message> handleException(Exception exception) {
//        System.out.println(exception.getMessage());
//        ErrorResponse response = new ErrorResponse(ErrorCode.SERVERERROR);
//        Message message = new Message(false, response);
//        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
