package com.example.Spotitube.datasource.dao;

import com.example.Spotitube.datasource.dbconnection.ConnectionManager;
import com.example.Spotitube.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public abstract class BaseDAO<T> {

    private ConnectionManager connectionManager;

    public ArrayList<T> getAll() {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = statementBuilder(connection, "SELECT", Optional.empty(), Optional.empty());
            ArrayList<T> result = buildFromResultSet(statement.executeQuery());
            connection.close();
            return result;
        } catch (SQLException e) { throw new DatabaseException(e.getMessage()); }
    }


    public T get(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = statementBuilder(connection, "SELECT", Optional.empty(), Optional.of(id));
            T result = buildFromResultSet(statement.executeQuery()).get(0);
            connection.close();
            return result;
        } catch (SQLException e) { throw new DatabaseException(e.getMessage()); }
    }

    public void insert(T t) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = statementBuilder(connection, "INSERT", Optional.of(t), Optional.empty());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) { throw new DatabaseException(e.getMessage()); }
    }

    public void update(T t, int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = statementBuilder(connection, "UPDATE", Optional.of(t), Optional.of(id));
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) { throw new DatabaseException(e.getMessage()); }
    }

    public void delete(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = statementBuilder(connection, "DELETE", Optional.empty(), Optional.of(id));
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) { throw new DatabaseException(e.getMessage()); }
    }

    public abstract PreparedStatement statementBuilder(Connection connection, String action, Optional<T> t, Optional<Integer> id) throws SQLException;

    public abstract ArrayList<T> buildFromResultSet(ResultSet rs) throws SQLException;

    public Connection getConnection() throws SQLException {
        return connectionManager.getConnection();
    }

    @Autowired
    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
}
