/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Marilyn
 */
public class Repository {
    Connection conn;

    public Repository() throws SQLException {
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        String dbUrl = "jdbc:derby://localhost:1527/venuedb;create=true";
        this.conn = DriverManager.getConnection(dbUrl, "root", "1234");
    }

    public Connection getConn() {
        return conn;
    }
}
