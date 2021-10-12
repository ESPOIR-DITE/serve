/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.controller.user;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mycompany.server.domain.ServerToken;
import com.mycompany.server.domain.user.UserCredentials;
import com.mycompany.server.factory.ServerTokenFactory;
import com.mycompany.server.factory.user.UserCredentialFactory;
import com.mycompany.server.repository.user.UserCridentialRepository;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marilyn
 */
public class UserCredentialController {
    private UserCridentialRepository userCredentialRepository;

    public UserCredentialController() {
        try {
            this.userCredentialRepository = new UserCridentialRepository();
        } catch (SQLException ex) {
            Logger.getLogger(UserCredentialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getUserCredentials(ServerToken serverToken){
        switch (serverToken.getRequest()){
            case "read": return read(serverToken);
            case "log-in": return logIn(serverToken);
            case "create-table": return create(serverToken);
            case "create": return createUserCredential(serverToken);
            //case "readAll": return readAll();
        }
        return null;
    }
    public String create(ServerToken serverToken){
        boolean userCredentials = userCredentialRepository.createTable();
        return userCredentials+"";
    }
    public String createUserCredential(ServerToken serverToken){
        UserCredentials userCredentials = UserCredentialFactory.getUserCredentialFromToken(serverToken.getValue());
        boolean result = userCredentialRepository.createUsersCredential(userCredentials);
        return result+"";
    }

    public String logIn(ServerToken serverToken) {
        UserCredentials userCredentials = userCredentialRepository.login(ServerTokenFactory.getLoginDetail(serverToken));
        System.out.println(userCredentials);
        return UserCredentialFactory.getUserCredentialString(userCredentials);
    }

    public String read(ServerToken serverToken){
        String id = serverToken.getValue();
        UserCredentials userCredential =  userCredentialRepository.read(id);
        return UserCredentialFactory.getUserCredentialString(userCredential);
    }
    public String readAll() {
        String listobjec = null;
       List<UserCredentials> userCredential =  userCredentialRepository.readAll();
        ObjectMapper mapper = new ObjectMapper();
        try {
             listobjec = mapper.writeValueAsString(userCredential);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            List<UserCredentials> list = mapper.readValue( listobjec,TypeFactory.defaultInstance().constructCollectionType(List.class, UserCredentials.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listobjec;
    }
    
}
