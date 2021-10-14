package com.mycompany.server.controller.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.server.domain.ServerToken;
import com.mycompany.server.domain.customer.Customer;
import com.mycompany.server.domain.user.Users;
import com.mycompany.server.factory.customer.CustomerFactory;
import com.mycompany.server.factory.user.UserFactory;
import com.mycompany.server.repository.Repository;
import com.mycompany.server.repository.customer.CustomerRepository;
import com.mycompany.server.repository.user.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private CustomerRepository customerRepository;

    public CustomerController() {
        try {
            this.customerRepository = new CustomerRepository();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public String getUser(ServerToken serverToken){
        switch (serverToken.getRequest()){
            case "read":
                return read(serverToken);
            case "create":
                return create(serverToken);
            case "update":
                return update(serverToken);
            case "reads":
                return readAll();
            case "delete":
                return delete(serverToken);
        }
        return null;
    }
    public boolean createTable(){
        return customerRepository.createTable();
    }

    public String read(ServerToken serverToken){
        String email = (String) serverToken.getValue();
        Customer users = customerRepository.read(email);
        return CustomerFactory.getCustomerFromObject(users);
    }
    public String create(ServerToken serverToken){
        Customer user = CustomerFactory.getCustomerFromValue(serverToken.getValue());
        return customerRepository.createUsers(user)+"";
    }
    public String update(ServerToken serverToken){
        Customer user = CustomerFactory.getCustomerFromValue(serverToken.getValue());
        return customerRepository.Update(user)+"";
    }
    public String delete(ServerToken serverToken){
        return customerRepository.delete(serverToken.getValue())+"";
    }
    public String readAll(){
        String listobjec = null;
        List<Customer> usersList= customerRepository.readAll();
        ObjectMapper mapper = new ObjectMapper();
        try {
            listobjec = mapper.writeValueAsString(usersList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listobjec;
    }
}
