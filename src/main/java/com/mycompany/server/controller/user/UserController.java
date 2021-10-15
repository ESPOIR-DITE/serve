/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mycompany.server.domain.ServerToken;
import com.mycompany.server.domain.user.UserCredentials;
import com.mycompany.server.domain.user.Users;
import com.mycompany.server.factory.ServerTokenFactory;
import com.mycompany.server.factory.user.UserFactory;
import com.mycompany.server.repository.user.UserRepository;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Marilyn
 */
public class UserController {
    private UserRepository userRepository;

    public UserController() {
        try {
            this.userRepository = new UserRepository();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getUser(ServerToken serverToken){
        switch (serverToken.getRequest()){
            case "read":
                return read(serverToken);
            case "create":
                return create(serverToken);
            case "update":
                return update(serverToken);
            case "reads":
                return readAll();
            case "delete":
                return delete(serverToken);

        }
        return null;
    }
    public boolean createTable(){
        return userRepository.createTable();
    }

    public String read(ServerToken serverToken){
       String email = (String) serverToken.getValue();
        Users users = userRepository.read(email);
        return UserFactory.getUserFromObject(users);
    }
    public String create(ServerToken serverToken){
        Users user = UserFactory.getUserFromValue(serverToken.getValue());
        return userRepository.createUsers(user)+"";
    }
    public String delete(ServerToken serverToken){
        return userRepository.delete(serverToken.getValue())+"";
    }
    public String update(ServerToken serverToken){
        Users user = UserFactory.getUserFromValue(serverToken.getValue());
        System.out.println(user);
        return userRepository.createUpdate(user)+"";
    }
    public String readAll(){
        String listobjec = null;
        List<Users> usersList= userRepository.readAll();
        ObjectMapper mapper = new ObjectMapper();
        try {
            listobjec = mapper.writeValueAsString(usersList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listobjec;
    }

}
