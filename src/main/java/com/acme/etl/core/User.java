package com.acme.etl.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author vm.andreev
 */
public class User {

	private final int id;
	private final String name;

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("<User id: %d, name: %s>", id, name);
	}

	public void prepareUserUpdate(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, name);
	}
}
