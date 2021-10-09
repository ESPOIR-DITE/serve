/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.repository.user;

import com.mycompany.server.domain.user.UserCredentials;
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
public class UserCridentialRepository {
    private final Connection connection = new Repository().getConn();
    private final Statement stmt;

    public UserCridentialRepository() throws SQLException {
        stmt = connection.createStatement();
    }
    public void createTable() throws SQLException {
      String query ="CREATE TABLE USERCREDENTIAL ("
              + "Id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,"
              + "EMAIL varchar(30),"
              + "PASSWORD varchar(30),"
              + "ACTIVES varchar(30), "
              + "USERCREATOR varchar(30),"
              + "USERTYPE varchar(30),"
              + "PRIMARY KEY (Id))";
        boolean resultSet = stmt.execute(query);

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
    public List<UserCredentials> readAll() throws SQLException {
        List<UserCredentials> users = null;
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM user_cridential ");
        while (resultSet.next()){
            users.add(UserCredentials.builder()
                    .id(resultSet.getString("id"))
                    .email(resultSet.getString("email"))
                    .password(resultSet.getString("password"))
                    .active(resultSet.getBoolean("active"))
                    .creator(resultSet.getString("creator"))
                    .userTypeId(resultSet.getString("user_type_id"))
                    .build());
        }
        return users;
    }
}
