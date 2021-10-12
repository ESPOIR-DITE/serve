package com.mycompany.server.controller.user;

import com.mycompany.server.domain.user.Users;
import com.mycompany.server.repository.user.UserRepository;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    UserController userController = new UserController();
    UserRepository userRepository = new UserRepository();

    UserControllerTest() throws SQLException {
    }

    @Test
    void read() {
    }

    @Test
    void createTable(){
        userController.createTable();
    }

    @Test
    void create() {
        Users users = Users.builder()
                .name("padou")
                .surname("kabengele")
                .email("padou@kabengele")
                .date(new Date().toString())
                .build();
        userRepository.createUsers(users);
    }

    @Test
    void update() {
    }

    @Test
    void readAll() {
        System.out.println(userController.readAll());
    }
}