/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.repository.user;

import com.mycompany.server.domain.user.UserType;
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
public class UserTypeRepository {
    private  Connection connection = null;
    private  Statement stmt;

    public UserTypeRepository() {
        try {
            connection =new  Repository().getConn();
            stmt = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void createUserTypeTable() throws SQLException {
        String query ="CREATE TABLE USERCREDENTIAL ("
              + "Id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,"
              + "USERTYPE varchar(30),"
              + "DESCRIPTION varchar(250),"
              + "PRIMARY KEY (Id))";
        stmt.execute(query);
    }
    
    public UserType read(String ID) {
        UserType userType = null;
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * FROM USERTYPE where ID="+ID);

        while (resultSet.next()){
            userType = UserType.builder()
                    .id(resultSet.getString("ID"))
                    .typeName(resultSet.getString("TYPENAME"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .build();
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return userType;
    }
    public boolean createUserType(UserType userType) {
        String query ="insert into USERTYPE values("+userType.getId()+","+userType.getTypeName()+","+userType.getDescription()+")";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
       return true;
    }
    public boolean updateUserType(UserType userType) {
        int result = 0;
        try {
            result = stmt.executeUpdate(userType.getId()+"update USERTYPE SET TYPENAME="+userType.getTypeName()+", DESCRIPTION="+userType.getDescription()+" WHERE ID="+userType.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public List<UserType> readAll() {
        List<UserType> users = null;
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * FROM USERTYPE ");
        while (resultSet.next()){
            UserType userTypeObject = UserType.builder()
                    .id(resultSet.getString("ID"))
                    .typeName(resultSet.getString("TYPENAME"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .build();
            users.add(userTypeObject);
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return users;
    }
}
