/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.controller.user;

import com.mycompany.server.domain.ServerToken;
import com.mycompany.server.domain.user.UserCredentials;
import com.mycompany.server.factory.ServerTokenFactory;
import com.mycompany.server.repository.user.UserRepository;
import java.sql.SQLException;

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

    public ServerToken getUser(ServerToken serverToken){
        switch (serverToken.getRequest()){
            case "log-in":
                try {
                    return logIn(serverToken);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

        }
        return null;
    }
    public ServerToken logIn(ServerToken serverToken) throws SQLException {
        UserCredentials userCridentials = userRepository.login(ServerTokenFactory.getLoginDetail(serverToken));
        return ServerTokenFactory.makeResponse(userCridentials);
    }
}
