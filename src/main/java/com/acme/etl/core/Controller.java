package com.acme.etl.core;

import com.acme.etl.exceptions.ETLException;
import com.acme.etl.exceptions.UserReaderException;
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
        Collection<User> users;
        try {
            users = userReader.readUsers();
        } catch (UserReaderException e) {
            e.printStackTrace();
            throw new ETLException("Can not extract users", e);
        }
        while (users != null && users.size() > 0) {
            for (UserWriter userWriter : userWriters) {
                userWriter.save(users);
            }
            try {
                users = userReader.readUsers();
            } catch (UserReaderException e) {
                e.printStackTrace();
                throw new ETLException("Can not extract users", e);
            }
        }
    }

}
