/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.repository;

import com.mycompany.server.domain.user.UserCredentials;
import com.mycompany.server.domain.user.Users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

/**
 *
 * @author Marilyn
 */
public class NewClass {
     static Statement stmt;
     static List<Users> users= new ArrayList();
    public static void main(String[] args){
        try{
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            //String dbUrl = "jdbc:derby:C:/Users/Marilyn/Documents/NetBeansProjects/server/src/main/java/com/mycompany/server/repository/db/venuedb";
            String dbUrl = "jdbc:derby://localhost:1527/venuedb;create=true";
            Connection con = DriverManager.getConnection(dbUrl, "root", "1234");
            System.out.println("connection");
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM USERS");
        while (resultSet.next()){
            Users userObject = Users.builder()
                    .email(resultSet.getString("EMAIL"))
                    .name(resultSet.getString("NAME"))
                    .surname(resultSet.getString("SURNAME"))
                    .date(resultSet.getString("DATE"))
                    .build();
            users.add(userObject);
            //System.out.print(resultSet.getString("EMAIL"));
        }
        for(Users user:users){
             System.out.println(user+"\n");
        }
        }catch(SQLException ex){
            System.out.print("exceprion\n"+ex);
        }
    }
   
}
