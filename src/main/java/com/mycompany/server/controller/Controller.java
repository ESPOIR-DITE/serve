/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.controller;

import com.mycompany.server.controller.user.UserController;
import com.mycompany.server.domain.ServerToken;

/**
 *
 * @author Marilyn
 */
public class Controller {
    private final UserController userController;

    public Controller() {
        this.userController = new UserController();
    }

    public ServerToken getServerToken(ServerToken serverToken){
        switch (serverToken.getDomain()){
            case "user": return userController.getUser(serverToken);

        }
        return null;
    }
}
