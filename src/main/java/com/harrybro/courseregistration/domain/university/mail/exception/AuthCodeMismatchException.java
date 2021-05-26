package com.harrybro.courseregistration.domain.university.mail.exception;

public class AuthCodeMismatchException extends IllegalArgumentException{

    public AuthCodeMismatchException(EmailExceptionMessage m) {
        super(m.getMessage());
    }

}
