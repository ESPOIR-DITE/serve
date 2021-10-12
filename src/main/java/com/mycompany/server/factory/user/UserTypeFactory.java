package com.mycompany.server.factory.user;

import com.mycompany.server.domain.user.UserType;
import com.mycompany.server.domain.user.Users;

import java.util.StringTokenizer;

public class UserTypeFactory {
    public static UserType getUserTypeFromValue(String value){
        StringTokenizer st = new StringTokenizer(value,"/");
        return UserType.builder()
                .id(st.nextToken())
                .typeName(st.nextToken())
                .description(st.nextToken()) // for now.
                .build();
    }

}
