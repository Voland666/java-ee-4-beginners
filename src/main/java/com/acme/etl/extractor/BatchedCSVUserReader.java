package com.acme.etl.extractor;

import com.acme.etl.core.User;
import com.acme.etl.exceptions.UserReaderException;
import com.acme.etl.exceptions.UserWriterException;
import com.acme.etl.loader.UserWriter;

import java.util.*;

/**
 * Created by vm.andreev on 16.01.17.
 */
public class BatchedCSVUserReader implements UserReader, AutoCloseable {

    private final CSVUserReader csvUserReader;

    public BatchedCSVUserReader(CSVUserReader csvUserReader) {
        this.csvUserReader = csvUserReader;
    }

    private Collection<User> parseUsers(Collection<String> batch) {
        Collection<User> users = new ArrayList<>(batch.size());
        for (String userLine : batch) {
            String[] userParameters = userLine.split(",");
            users.add(new User(Integer.parseInt(userParameters[0]), userParameters[1]));
        }
        return users;

    }
    @Override
    public void readAndSave(UserWriter... userWriters) throws UserReaderException, UserWriterException {
        for (Collection<String> batch : csvUserReader) {
            Collection<User> batchUsers = parseUsers(batch);
            for (UserWriter userWriter : userWriters) {
                userWriter.save(batchUsers);
            }
        }
    }

    @Override
    public void close() throws Exception {
        this.csvUserReader.close();
    }

}
