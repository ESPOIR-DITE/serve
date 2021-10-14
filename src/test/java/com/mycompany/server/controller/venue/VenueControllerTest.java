package com.mycompany.server.controller.venue;

import com.mycompany.server.domain.venue.Venue;
import com.mycompany.server.repository.venue.VenueRepository;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class VenueControllerTest {
VenueController venueController = new VenueController();
VenueRepository venueRepository = new VenueRepository();

    VenueControllerTest() throws SQLException {
    }

    @Test
    void getUserCredentials() {
    }

    @Test
    void read() {
        //System.out.println(venueController.read()
    }

    @Test
    void createTable() {
    }

    @Test
    void createVenue() {
        Venue venue = Venue.builder()
                .categoryId("oieu")
                .description("test")
                .maxNumGuest(12)
                .cost(200)
                .location("test")
                .date("Tue Oct 12 13:21:57 SAST 2021")
                .availability(true)
                .name("test")
                .build();
        System.out.println(venueRepository.createVenue(venue));
    }

    @Test
    void update() {
        Venue venue = Venue.builder()
                .id(101)
                .categoryId("mbuyi")
                .description("test")
                .maxNumGuest(12)
                .cost(200)
                .location("test")
                .date("Tue Oct 12 13:21:57 SAST 2021")
                .availability(true)
                .name("test")
                .build();
        System.out.println(venueRepository.createVenue(venue));
    }

    @Test
    void readAll() {
        System.out.println(venueController.readAllUnAvailable());
    }

    @Test
    void testUpdate() {
        System.out.println(venueController.readAllAvailable());
    }
}