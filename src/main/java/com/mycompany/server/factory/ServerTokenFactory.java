/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.server.domain.ServerToken;
import com.mycompany.server.domain.user.UserCredentials;
import com.mycompany.server.domain.user.Users;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

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
        String email =null,password =null;
        try{
            StringTokenizer st = new StringTokenizer(serverToken.getValue(),"/");
            if(st.hasMoreTokens()){
               email = st.nextToken();
               password = st.nextToken();
            }
            if(!email.equals("")&&!password.equals("")){
                return UserCredentials.builder()
                        .email(email).password(password).build();
            }
            System.out.println( email+" "+password);
        }catch(NullPointerException nullPointerException){
            return null;
        }
            return null;
    }
    public static ServerToken makeResponse(Object object){
        return ServerToken.builder()
                .date(new Date())
                .requestType("log-in-response")
                .value((String) object)
                .build();
    }
    public static ServerToken makeListResponse(List<Object> objects){
        return ServerToken.builder()
                .date(new Date())
                .requestType("log-in-response")
//                .responses(objects)
                .build();
    }
    public static ServerToken makeWrongRequest(){
        String response = "Wrong mapping";
        return ServerToken.builder()
                .date(new Date())
                .requestType("log-in-response")
                .value(response)
                .build();
    }
    public static ServerToken getServerToken(String value){
        ServerToken parties1 = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
             parties1 = mapper.readValue(value,ServerToken.class);
            //System.out.println(parties1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return parties1;
    }
    public ServerToken getServerTokenString(ServerToken serverToken){
        return ServerToken.builder()
                .users(getUser(serverToken.getUsers()))
                .value(serverToken.getValue())
                .date(serverToken.getDate())
                .domain(serverToken.getDomain())
                .request(serverToken.getRequest())
                .requestType(serverToken.getRequestType()).build();

    }
    Users getUser(Users users){
        try{
            return Users.builder()
                    .email(users.getEmail())
                    .date(users.getDate())
                    .surname(users.getSurname())
                    .name(users.getName())
                    .build();
        }catch (NullPointerException nullPointerException){
            System.out.println("exception");
            return null;
        }
    }
}
