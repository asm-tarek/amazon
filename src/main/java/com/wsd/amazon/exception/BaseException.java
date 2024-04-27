package com.wsd.amazon.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseException extends RuntimeException {
    private long functionCallIdentifier;
    private String subject;
    private String subjectIdentifier;

    public BaseException(long functionCallIdentifier, String subject, String subjectIdentifier, Exception exception) {
        super(exception);
        this.setSubjectIdentifier(subjectIdentifier);
        this.setFunctionCallIdentifier(functionCallIdentifier);
        this.setSubject(subject);
        this.setSubjectIdentifier(subjectIdentifier);
    }

    public BaseException(Throwable exception) {
        super(exception);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException() {
    }

}