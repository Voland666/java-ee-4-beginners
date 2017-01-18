package com.acme.etl.extractor;

import com.acme.etl.core.User;
import com.acme.etl.exceptions.UserReaderException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by vm.andreev on 16.01.17.
 */
public class CSVUserReader implements UserReader {

    private BatchedBufferedReader batchedBufferedReader;

    public CSVUserReader(BatchedBufferedReader batchedBufferedReader) {
        this.batchedBufferedReader = batchedBufferedReader;
    }

    @Override
    public Collection<User> readUsers() throws UserReaderException {
        if (batchedBufferedReader.hasNext()) {
            Collection<String> userLines = batchedBufferedReader.next();
            Collection<User> users = new ArrayList<>(userLines.size());
            for (String userLine : userLines) {
                String[] userParameters = userLine.split(",");
                users.add(new User(Integer.parseInt(userParameters[0]), userParameters[1]));
            }
            return users;
        } else {
            return null;
        }
    }

    @Override
    public void close() throws IOException {
        this.batchedBufferedReader.close();
    }
}
