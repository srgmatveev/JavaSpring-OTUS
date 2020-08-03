package org.sergio.library.exceptions;

public class PersonDaoException extends Exception {
    public PersonDaoException() {
    }

    public PersonDaoException(String message) {
        super(message);
    }

    public PersonDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonDaoException(Throwable cause) {
        super(cause);
    }

    public PersonDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
