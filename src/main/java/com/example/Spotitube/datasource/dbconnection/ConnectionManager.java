package com.example.Spotitube.datasource.dbconnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionManager {
    private DataProperties properties;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(properties.getConnectionString());
    }

    @Autowired
    public void setDataProperties(DataProperties properties) {
        this.properties = properties;
    }

}