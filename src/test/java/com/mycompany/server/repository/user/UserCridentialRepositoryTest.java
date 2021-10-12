package com.mycompany.server.repository.user;

import com.mycompany.server.domain.user.UserCredentials;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserCridentialRepositoryTest {
    UserCridentialRepository userCridentialRepository = new UserCridentialRepository();

    UserCridentialRepositoryTest() throws SQLException {
    }

    @Test
    void createUsersCredential() {
        UserCredentials userCredentials = UserCredentials.builder()
                .userTypeId("test")
                .creator("espoir")
                .active(true)
                .password("123")
                .email("elwkdskl@sdd.com")
                .build();
        userCridentialRepository.createUsersCredential(userCredentials);
    }

    @Test
    void createTable() {
    }

    @Test
    void login() {
    }

    @Test
    void read() {
    }

    @Test
    void readAll() {
    }
}