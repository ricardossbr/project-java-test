package com.example.projectjavatest.exception;

public class TheUserException extends Throwable{
    public TheUserException(){
        super("The user does not exist!");
    }

    public TheUserException(String message){
        super(message);
    }

    public TheUserException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }
}