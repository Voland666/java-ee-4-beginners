package com.acme.etl.core;

import com.acme.etl.exceptions.ETLException;
import com.acme.etl.exceptions.UserReaderException;
import com.acme.etl.exceptions.UserWriterException;
import com.acme.etl.extractor.UserReader;
import com.acme.etl.loader.UserWriter;

import java.util.Collection;

/**
 * @author vm.andreev
 */
public class Controller {

    private final UserReader userReader;
    private final UserWriter[] userWriters;

    /**
     * @param userReader
     * @param userWriters
     */
    public Controller(UserReader userReader, UserWriter... userWriters) {
        this.userReader = userReader;
        this.userWriters = userWriters;
    }

    public void doETL() throws ETLException {
        try {
            userReader.readAndSave(userWriters);
        } catch (UserReaderException e) {
            throw new ETLException("Can not extract users", e);
        } catch (UserWriterException e) {
            throw new ETLException("Can not load users", e);
        }
    }

}
