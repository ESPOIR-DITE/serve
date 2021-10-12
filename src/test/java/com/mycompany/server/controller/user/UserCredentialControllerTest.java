package com.mycompany.server.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.server.domain.user.UserCredentials;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserCredentialControllerTest {
    UserCredentialController ucc = new UserCredentialController();

    @Test
    void getUserCredentials() {
    }

    @Test
    void create() {
    }

    @Test
    void logIn() {
    }

    @Test
    void read() {
    }

    @Test
    void readAll() throws JsonProcessingException {
        System.out.println(ucc.readAll());
        String value = ucc.readAll();

        ObjectMapper mapper = new ObjectMapper();
        UserCredentials[] pp1 = mapper.readValue(value, UserCredentials[].class);
        for (UserCredentials party: pp1){
            //System.out.println(party.getName());
            System.out.println(party.getActive());
        }
    }
}