package com.mycompany.server.repository.customer;

import com.mycompany.server.domain.customer.Customer;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {
    CustomerRepository customerRepository = new CustomerRepository();

    CustomerRepositoryTest() throws SQLException {
    }

    @Test
    void createTable() {
        System.out.println(customerRepository.createTable());
    }

    @Test
    void read() {
        System.out.println(customerRepository.read("espoir@gmail.com"));
    }

    @Test
    void delete() {
        System.out.println(customerRepository.delete("1"));
    }

    @Test
    void createUsers() {
        Customer customer = Customer.builder()
                .email("espoir@gmail.com")
                .name("espoir")
                .surname("ditekemena")
                .date(new Date().toString())
                .build();
        System.out.println(customerRepository.createUsers(customer));
    }


    @Test
    void readAll() {
        System.out.println(customerRepository.readAll());
    }

    @Test
    void testCreateTable() {
    }

    @Test
    void testRead() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void testCreateUsers() {
    }

    @Test
    void update() {
    }

    @Test
    void testReadAll() {
    }
}