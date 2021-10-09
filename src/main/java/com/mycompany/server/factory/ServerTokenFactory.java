/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.factory;

import com.mycompany.server.domain.ServerToken;
import com.mycompany.server.domain.user.UserCredentials;
import com.mycompany.server.domain.user.Users;
import java.util.Date;

/**
 *
 * @author Marilyn
 */
public class ServerTokenFactory {
    public ServerToken makeRequest(Users users,String requestType,String value){
        return ServerToken.builder()
                .date(new Date())
                .requestType(requestType)
                .users(users)
                .value(value)
                .build();
    }
    public static UserCredentials getLoginDetail(ServerToken serverToken){
        return (UserCredentials) serverToken.getValue();
    }
    public static ServerToken makeResponse(Object object){
        return ServerToken.builder()
                .date(new Date())
                .requestType("log-in-response")
                .response(object)
                .build();
    }
}
