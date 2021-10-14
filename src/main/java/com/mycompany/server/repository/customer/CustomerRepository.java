package com.mycompany.server.repository.customer;

import com.mycompany.server.domain.customer.Customer;
import com.mycompany.server.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private final Connection connection = new Repository().getConn();
    private Statement stmt;

    public CustomerRepository() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean createTable()  {
        // create table
        boolean resultSet = false;
        try {
            String query = "CREATE TABLE CUSTOMER (" +
                    "Id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY," +
                    "NAME varchar(30)," +
                    "EMAIL varchar(30)," +
                    "SURNAME varchar(30)," +
                    "DATE varchar(30)," +
                    "PRIMARY KEY (Id))";
            resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public Customer read(String email)  {
        Customer customer = null;
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * FROM CUSTOMER where EMAIL='"+email+"'");
            while (resultSet.next()){
                customer = Customer.builder()
                        .email(resultSet.getString("EMAIL"))
                        .name(resultSet.getString("NAME"))
                        .surname(resultSet.getString("SURNAME"))
                        .date(resultSet.getString("DATE"))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }
    public boolean delete(String email)  {
        try {
            stmt.executeUpdate("DELETE FROM CUSTOMER where EMAIL='"+email+"'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean createUsers(Customer customer)  {
        try {
            String query = "INSERT INTO CUSTOMER (EMAIL,NAME,SURNAME,DATE) VALUES ('"+customer.getEmail()+"','"+customer.getName()+"','"+customer.getSurname()+"','"+customer.getDate()+"')";
            stmt.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean Update(Customer customer)  {
        String query = "UPDATE CUSTOMER SET NAME=?,SURNAME=? WHERE EMAIL=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getSurname());
            ps.setString(3,customer.getEmail());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public List<Customer> readAll() {
        List<Customer> customers = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * FROM CUSTOMER ");

            while (resultSet.next()){
                customers.add(Customer.builder()
                        .date(resultSet.getString("date"))
                        .email(resultSet.getString("email"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .build());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return customers;
    }
}
