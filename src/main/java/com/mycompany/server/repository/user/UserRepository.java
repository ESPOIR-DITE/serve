/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.repository.user;

import com.mycompany.server.domain.user.UserCredentials;
import com.mycompany.server.domain.user.Users;
import com.mycompany.server.repository.Repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marilyn
 */
public class UserRepository {
    private final Connection connection = new  Repository().getConn();
    private Statement stmt;

    public UserRepository() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean createTable()  {
        // create table
        boolean resultSet = false;
        try {
            String query = "CREATE TABLE USERS (" +
                    "Id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY," +
                    "EMAIL varchar(30)," +
                    "NAME varchar(30)," +
                    "SURNAME varchar(30)," +
                    "Date varchar(30)," +
                    "PRIMARY KEY (Id))";
            resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public Users read(String email)  {
        Users users = null;
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * FROM USERS where EMAIL='"+email+"'");
        while (resultSet.next()){
            users = Users.builder()
                    .email(resultSet.getString("email"))
                    .date(resultSet.getString("date"))
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .build();
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
    public boolean createUsers(Users users)  {
        try {
            String query = "INSERT INTO USERS (EMAIL,NAME,SURNAME,DATE) VALUES ('"+users.getEmail()+"','"+users.getName()+"','"+users.getSurname()+"','"+users.getDate()+"')";
            stmt.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean createUpdate(Users users)  {
        try {
            stmt.executeUpdate("update into USERS values('"+users.getEmail()+"',"+users.getName()+","+users.getSurname()+","+users.getDate()+")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public List<Users> readAll() {
        List<Users> users = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * FROM USERS ");

        while (resultSet.next()){
            users.add(Users.builder()
                    .email(resultSet.getString("email"))
                    .date(resultSet.getString("date"))
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .build());
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return users;
    }
}
