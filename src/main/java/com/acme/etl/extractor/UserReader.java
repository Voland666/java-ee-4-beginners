package com.acme.etl.extractor;

import com.acme.etl.core.User;
import com.acme.etl.exceptions.UserReaderException;
import com.acme.etl.exceptions.UserWriterException;
import com.acme.etl.loader.UserWriter;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;

/**
 * @author vm.andreev
 */
public interface UserReader extends AutoCloseable {

    void readAndSave(UserWriter... userWriters) throws UserReaderException, UserWriterException;

    void close() throws Exception;
}
