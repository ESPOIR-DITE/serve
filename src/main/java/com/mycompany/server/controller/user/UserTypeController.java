package com.mycompany.server.controller.user;

import com.mycompany.server.domain.ServerToken;
import com.mycompany.server.domain.user.UserType;
import com.mycompany.server.factory.ServerTokenFactory;
import com.mycompany.server.factory.user.UserTypeFactory;
import com.mycompany.server.repository.user.UserTypeRepository;

import java.util.Collections;
import java.util.List;

public class UserTypeController {
    private final UserTypeRepository userTypeRepository;

    public UserTypeController() {
        this.userTypeRepository = new UserTypeRepository();
    }
    public ServerToken getUserType(ServerToken serverToken){
        switch (serverToken.getRequest()){
            case "read":
                return read(serverToken);
            case "reads":
                return readAll();
            case "create":
                return create(serverToken);
            case "update":
                return update(serverToken);
        }
        return ServerTokenFactory.makeWrongRequest();
    }

    public ServerToken read(ServerToken serverToken){
        String id =(String)serverToken.getValue();
        return ServerTokenFactory.makeResponse(userTypeRepository.read(id));
    }
    public ServerToken create(ServerToken serverToken){
        UserType userType = UserTypeFactory.getUserTypeFromValue(serverToken.getValue());
        boolean result = userTypeRepository.createUserType(userType);
        return ServerTokenFactory.makeResponse(result);
    }
    public ServerToken update(ServerToken serverToken){
        UserType userType = UserTypeFactory.getUserTypeFromValue(serverToken.getValue());
        return ServerTokenFactory.makeResponse(userTypeRepository.updateUserType(userType));
    }
    public ServerToken readAll(){
        List<Object> objects = Collections.singletonList((Object) userTypeRepository.readAll());
        return ServerTokenFactory.makeListResponse(objects);
    }
}
