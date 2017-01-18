package com.acme.etl.exceptions;

/**
 * Created by vm.andreev on 18.01.17.
 */
public class UserWriterException extends Exception {
    private String connection = null;

    public UserWriterException(String connection, String message, Throwable cause) {
        this(String.format("[%s]: %s", connection, message), cause);
        this.connection = connection;
    }
    public UserWriterException(String message, Throwable cause) {
        super(message, cause);
    }
}
