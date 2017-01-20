package com.acme.etl.core;

import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author vm.andreev
 */
public class User implements Serializable {

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

    public Attribute getUserIdAttribute(String attributeName) {
        return new BasicAttribute(attributeName, String.valueOf(id));
    }

    public Attribute getUserNameAttribute(String attributeName) {
        return new BasicAttribute(attributeName, name);
    }
}
