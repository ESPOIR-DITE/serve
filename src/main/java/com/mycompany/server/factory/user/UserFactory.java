package com.mycompany.server.factory.user;

import com.mycompany.server.domain.user.Users;

import java.util.StringTokenizer;

public class UserFactory {
    public static String getUserFromObject(Users users){
        return users.getEmail()+"/"+ users.getName()+"/"+ users.getSurname()+"/"+ users.getDate();
    }
    public static Users getUserFromValue(String value){
        StringTokenizer st = new StringTokenizer(value,"/");
        return Users.builder()
                .email(st.nextToken())
                .name(st.nextToken())
                .surname(st.nextToken()) // for now.
                .date(st.nextToken())
                .build();
    }
}
