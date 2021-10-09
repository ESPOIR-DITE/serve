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
import java.util.List;

/**
 *
 * @author Marilyn
 */
public class UserRepository {
    private final Connection connection = new  Repository().getConn();
    private final Statement stmt;

    public UserRepository() throws SQLException {
        stmt = connection.createStatement();
    }
    public void createTable() throws SQLException {
        // create table
       int resultSet = stmt.executeUpdate("Create table users (email varchar(30) primary key, name varchar(30)),surname varchar(30)),date data(30))");
        System.out.println(resultSet);
    }
    public UserCredentials login(UserCredentials userCredentials) throws SQLException {
        UserCredentials users = null;
       ResultSet resultSet = stmt.executeQuery("SELECT * FROM user_cridential where email="+ userCredentials.getEmail()+" and password="+ userCredentials.getPassword()+"");
       while (resultSet.next()){
           users = UserCredentials.builder()
                   .id(resultSet.getString("id"))
                   .email(resultSet.getString("email"))
                   .password(resultSet.getString("password"))
                   .active(resultSet.getBoolean("active"))
                   .creator(resultSet.getString("creator"))
                   .userTypeId(resultSet.getString("user_type_id"))
                   .build();
       }
       return users;
    }
    public Users read(String email) throws SQLException {
        Users users = null;
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM users where email="+email+"");
        while (resultSet.next()){
            users = Users.builder()
                    .email(resultSet.getString("email"))
                    .date(resultSet.getDate("date").toLocalDate())
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .build();
        }
        return users;
    }
    public void createUsers(Users users) throws SQLException {
        stmt.executeUpdate("insert into users values("+users.getEmail()+","+users.getName()+","+users.getSurname()+","+users.getDate()+")");
    }
    public void createUpdate(Users users) throws SQLException {
        stmt.executeUpdate("update into users values("+users.getEmail()+","+users.getName()+","+users.getSurname()+","+users.getDate()+")");
    }
    public List<Users> readAll(String userName, String password) throws SQLException {
        List<Users> users = null;
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM users ");
        while (resultSet.next()){
            users.add(Users.builder()
                    .email(resultSet.getString("email"))
                    .date(resultSet.getDate("date").toLocalDate())
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .build());
        }
        return users;
    }
}
