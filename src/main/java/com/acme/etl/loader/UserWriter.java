package com.acme.etl.loader;

import com.acme.etl.core.User;
import com.acme.etl.exceptions.UserWriterException;

import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author vm.andreev
 */
public interface UserWriter {

	void save(Collection<User> users) throws UserWriterException;
}
