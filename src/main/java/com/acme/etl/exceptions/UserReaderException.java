package com.acme.etl.exceptions;

import java.io.IOException;

/**
 * Created by vm.andreev on 18.01.17.
 */
public class UserReaderException extends IOException {
    public UserReaderException() {
        super();
    }

    public UserReaderException(String message) {
        super(message);
    }

    public UserReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
