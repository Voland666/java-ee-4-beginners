package com.acme.etl.loader;

import com.acme.etl.core.User;
import com.acme.etl.exceptions.UserWriterException;

import java.nio.file.DirectoryIteratorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by vm.andreev on 18.01.17.
 */
public class DBUserWriter implements UserWriter {

    private final String url;

    public DBUserWriter(String url) {
        this.url = url;
    }

    @Override
    public void save(Collection<User> users) throws UserWriterException {
        PreparedStatement preparedStatement = null;
        try (Connection connection = DriverManager.getConnection(this.url)) {
            preparedStatement = connection.prepareStatement("INSERT INTO USERS VALUES (?, ?)");
            for (User user : users) {
                try {
                    user.prepareUserUpdate(preparedStatement);
                    preparedStatement.executeUpdate();
                    System.out.println("Saved: " + user + " to DB");
                } catch (SQLException e) {
                    throw new UserWriterException(url, "Database update failed for user " + user, e);
                }
            }
        } catch (SQLException e) {
            throw new UserWriterException("Error during preparing statement " + preparedStatement, e);
        }
    }
}
