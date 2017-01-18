package com.acme.etl.extractor;

import com.acme.etl.core.User;
import com.acme.etl.exceptions.UserReaderException;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;

/**
 * @author vm.andreev
 */
public interface UserReader extends AutoCloseable {

    Collection<User> readUsers() throws UserReaderException;

    void close() throws IOException;
}
