package com.acme.etl.loader;

import com.acme.etl.core.User;

import java.util.Collection;

/**
 *
 * @author vm.andreev
 */
public interface UserWriter {

	void save(Collection<User> users);
}
