package org.sergio.library.exceptions;

public class UniqueFileUploadException extends Exception{
    public UniqueFileUploadException() {
        super();
    }

    public UniqueFileUploadException(String message) {
        super(message);
    }
}
