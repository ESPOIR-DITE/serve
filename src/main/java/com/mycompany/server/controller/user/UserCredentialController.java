/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.controller.user;

import com.mycompany.server.domain.ServerToken;
import com.mycompany.server.repository.user.UserCridentialRepository;
import java.sql.SQLException;
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
    public ServerToken getUser(ServerToken serverToken){
        switch (serverToken.getRequest()){
            case "read":
                try {
                    return read((String)serverToken.getValue());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

        }
        return null;
    }
    public ServerToken read(String id){
        UserCredentials userCredential =  UserCridentialRepository.
    }
    
}
