package com.example.projectjavatest.exception;

public class TeamAndUserException extends Throwable{
    public TeamAndUserException(){
        super("The TeamAndUser does not exist!");
    }

    public TeamAndUserException(String message){
        super(message);
    }

    public TeamAndUserException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }
}