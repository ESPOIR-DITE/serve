package com.mycompany.server;

import com.mycompany.server.domain.venue.UserVenue;
import com.mycompany.server.repository.booking.BookingRepository;
import com.mycompany.server.repository.customer.CustomerRepository;
import com.mycompany.server.repository.user.UserCridentialRepository;
import com.mycompany.server.repository.user.UserRepository;
import com.mycompany.server.repository.venue.VenueRepository;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ServerAppTest {
    CustomerRepository customerRepository = new CustomerRepository();
    UserRepository userRepository = new UserRepository();
    VenueRepository venueRepository = new VenueRepository();
    UserCridentialRepository userCridentialRepository = new UserCridentialRepository();
    BookingRepository bookingRepository = new BookingRepository();

    ServerAppTest() throws SQLException {
    }


    @Test
    void createTablesTest() {
        System.out.println("customer table create: "+customerRepository.createTable());
        System.out.println("user table create: "+ userRepository.createTable());
        System.out.println("venue table create: "+ venueRepository.createVenueRepositoryTable());
        System.out.println("user credential table create: "+userCridentialRepository.createTable());
        System.out.println("booking table create: "+bookingRepository.createTable());
    }
}