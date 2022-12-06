package com.example.projectjavatest.exception;

public class TheTeamException extends Throwable{
    public TheTeamException(){
        super("The team does not exist!");
    }

    public TheTeamException(String message){
        super(message);
    }

    public TheTeamException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }
}
