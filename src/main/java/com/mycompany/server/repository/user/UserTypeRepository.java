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
    private final Connection connection = new  Repository().getConn();
    private final Statement stmt;

    public UserTypeRepository() throws SQLException {
        stmt = connection.createStatement();
    }
    public void createUserTypeTable() throws SQLException {
        String query ="CREATE TABLE USERCREDENTIAL ("
              + "Id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,"
              + "USERTYPE varchar(30),"
              + "DESCRIPTION varchar(250),"
              + "PRIMARY KEY (Id))";
        stmt.execute(query);
    }
    
    public UserType read(String ID) throws SQLException {
        UserType userType = null;
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM USERTYPE where ID="+ID);
        while (resultSet.next()){
            userType = UserType.builder()
                    .id(resultSet.getString("ID"))
                    .typeName(resultSet.getString("TYPENAME"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .build();
        }
        return userType;
    }
    public void createUserType(UserType userType) throws SQLException {
        String query ="insert into USERTYPE values("+userType.getId()+","+userType.getTypeName()+","+userType.getDescription()+")";
        int reslt = stmt.executeUpdate(query);
        System.out.println("restl: "+reslt);
    }
    public void UpdateUserType(UserType userType) throws SQLException {
        int result = stmt.executeUpdate(userType.getId()+"update USERTYPE SET TYPENAME="+userType.getTypeName()+", DESCRIPTION="+userType.getDescription()+" WHERE ID="+userType.getId());
        System.out.println("restl: "+result);
    }
    public List<UserType> readAll() throws SQLException {
        List<UserType> users = null;
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM USERTYPE ");
        while (resultSet.next()){
            UserType userTypeObject = UserType.builder()
                    .id(resultSet.getString("ID"))
                    .typeName(resultSet.getString("TYPENAME"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .build();
            users.add(userTypeObject);
        }
        return users;
    }
}
