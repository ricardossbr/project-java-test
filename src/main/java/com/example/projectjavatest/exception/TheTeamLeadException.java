package com.example.projectjavatest.exception;

public class TheTeamLeadException extends Throwable {
    public TheTeamLeadException() {
        super("The team lead does not exist!");
    }

    public TheTeamLeadException(String message) {
        super(message);
    }

    public TheTeamLeadException(String message, Throwable causeOfError) {
        super(message, causeOfError);
    }
}