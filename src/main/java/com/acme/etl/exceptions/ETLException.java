package com.acme.etl.exceptions;

/**
 * Created by vm.andreev on 18.01.17.
 */
public class ETLException extends Exception {
    public ETLException(String message, Throwable cause) {
        super(message, cause);
    }
}
