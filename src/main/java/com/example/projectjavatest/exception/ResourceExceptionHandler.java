package com.example.projectjavatest.exception;

import com.example.projectjavatest.dto.DetailHttp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(TheTeamException.class)
    public ResponseEntity<DetailHttp> handleTheTeamException(TheTeamException ex, HttpServletRequest request){
        final DetailHttp error = new DetailHttp();
        error.setCode("404");
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(TheTeamLeadException.class)
    public ResponseEntity<DetailHttp> handleTheTeamLeadException(TheTeamLeadException ex, HttpServletRequest request){
        final DetailHttp error = new DetailHttp();
        error.setCode("400");
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(TheUserException.class)
    public ResponseEntity<DetailHttp> handleTheUserException(TheUserException ex, HttpServletRequest request){
        final DetailHttp error = new DetailHttp();
        error.setCode("400");
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(TeamAndUserException.class)
    public ResponseEntity<DetailHttp> handleTeamAndUserException(TeamAndUserException ex, HttpServletRequest request){
        final DetailHttp error = new DetailHttp();
        error.setCode("200");
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(error);
    }


}
