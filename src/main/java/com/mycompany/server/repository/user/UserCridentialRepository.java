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
public class UserCridentialRepository {
    private final Connection connection = new Repository().getConn();
    private final Statement stmt;

    public UserCridentialRepository() throws SQLException {
        stmt = connection.createStatement();
    }
    public boolean createUsersCredential(UserCredentials users)  {
        try {
            String query = "INSERT INTO USERCREDENTIAL (EMAIL,PASSWORD,ACTIVES,USERCREATOR,USERTYPE) VALUES ('"+users.getEmail()+"','"+users.getPassword()+"','"+users.getActive()+"','"+users.getCreator()+"','"+users.getUserTypeId()+"')";
            stmt.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean createTable() {
      String query ="CREATE TABLE USERCREDENTIAL ("
              + "Id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,"
              + "EMAIL varchar(30),"
              + "PASSWORD varchar(30),"
              + "ACTIVES varchar(30), "
              + "USERCREATOR varchar(30),"
              + "USERTYPE varchar(30),"
              + "PRIMARY KEY (Id))";
        boolean resultSet = false;
        try {
            resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public UserCredentials login(UserCredentials userCredentials) {
        System.out.println(userCredentials);
        UserCredentials users = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT ID,EMAIL,PASSWORD,ACTIVES,USERCREATOR,USERTYPE FROM USERCREDENTIAL where EMAIL='"+userCredentials.getEmail()+"' and PASSWORD='"+userCredentials.getPassword()+"'";
            System.out.println("query: "+query);
            resultSet = stmt.executeQuery(query);
        while (resultSet.next()){
           users = UserCredentials.builder()
                   .id(resultSet.getString("ID"))
                   .email(resultSet.getString("EMAIL"))
                   .password(resultSet.getString("PASSWORD"))
                   .active(resultSet.getBoolean("ACTIVES"))
                   .creator(resultSet.getString("USERCREATOR"))
                   .userTypeId(resultSet.getString("USERTYPE"))
                   .build();
       }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return users;
    }
    public UserCredentials read(String id)  {
        UserCredentials users = null;
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * FROM USERCREDENTIAL where ID="+id);

        while (resultSet.next()){
            users = UserCredentials.builder()
                    .id(resultSet.getString("ID"))
                    .email(resultSet.getString("EMAIL"))
                    .password(resultSet.getString("PASSWORD"))
                    .active(resultSet.getBoolean("ACTIVES"))
                    .creator(resultSet.getString("USERCREATOR"))
                    .userTypeId(resultSet.getString("USERTYPE"))
                    .build();
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
    public List<UserCredentials> readAll() {
        List<UserCredentials> userList = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * FROM USERCREDENTIAL ");

        while (resultSet.next()){
            UserCredentials users1 = UserCredentials.builder()
                    .id(resultSet.getString("ID"))
                    .email(resultSet.getString("EMAIL"))
                    .password(resultSet.getString("PASSWORD"))
                    .active(resultSet.getBoolean("ACTIVES"))
                    .creator(resultSet.getString("USERCREATOR"))
                    .userTypeId(resultSet.getString("USERTYPE"))
                    .build();
            userList.add(users1);
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return userList;
    }
}
