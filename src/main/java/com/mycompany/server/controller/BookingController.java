package com.mycompany.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.server.domain.Booking;
import com.mycompany.server.domain.ServerToken;
import com.mycompany.server.domain.user.Users;
import com.mycompany.server.factory.booking.BookingFactory;
import com.mycompany.server.factory.user.UserFactory;
import com.mycompany.server.repository.booking.BookingRepository;
import com.mycompany.server.repository.user.UserRepository;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;

public class BookingController {
    private BookingRepository bookingRepository;

    public BookingController() {
        try {
            this.bookingRepository = new BookingRepository();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public String getBooking(ServerToken serverToken){
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
        return bookingRepository.createTable();
    }
    public String read(ServerToken serverToken){
        String email = (String) serverToken.getValue();
        Booking users = bookingRepository.read(email);
        return BookingFactory.getBookingFromObject(users);
    }
    public String create(ServerToken serverToken){
        Booking user = BookingFactory.getBookingFromValue(serverToken.getValue());
        return bookingRepository.createBooking(user)+"";
    }
    public String update(ServerToken serverToken){
        Booking user = BookingFactory.getBookingFromValue(serverToken.getValue());
        System.out.println(user);
        return bookingRepository.Update(user)+"";
    }
    public String readAll(){
        String listobjec = null;
        List<Booking> usersList= bookingRepository.readAll();
        ObjectMapper mapper = new ObjectMapper();
        try {
            listobjec = mapper.writeValueAsString(usersList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listobjec;
    }
    public String delete(ServerToken serverToken){
        return bookingRepository.delete(serverToken.getValue())+"";
    }
}
