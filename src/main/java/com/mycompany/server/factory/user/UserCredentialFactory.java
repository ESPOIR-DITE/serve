package com.mycompany.server.factory.user;

import com.mycompany.server.domain.user.UserCredentials;

import java.util.StringTokenizer;

public class UserCredentialFactory {
    public static String getUserCredentialString(UserCredentials userCredentials){
        try{
            return  userCredentials.getId()+"/"+userCredentials.getEmail()+"/"+userCredentials.getPassword()+"/"+userCredentials.getActive()+"/"+userCredentials.getCreator()+"/"+userCredentials.getUserTypeId();
        }catch (NullPointerException nullPointerException){
            return null;
        }
    }
    public static UserCredentials getUserCredentialFromToken(String token){
        StringTokenizer st = new StringTokenizer(token,"/");
        return UserCredentials.builder()
                .id(st.nextToken())
                .email(st.nextToken())
                .password(st.nextToken())
                .active(Boolean.parseBoolean(st.nextToken()))
                .creator(st.nextToken())
                .userTypeId(st.nextToken()).build();
    }

}
