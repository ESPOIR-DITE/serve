package com.mycompany.server.repository.venue;

import com.mycompany.server.domain.venue.Venue;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class VenueRepositoryTest {
VenueRepository venueRepository = new VenueRepository();

    VenueRepositoryTest() throws SQLException {
    }

    @Test
    void createVenueRepositoryTable() {
        venueRepository.createVenueRepositoryTable();
    }

    @Test
    void readVenue() {
    }

    @Test
    void createVenue() {
        Venue venue = Venue.builder()
                .name("test")
                .maxNumGuest(12)
                .location("none")
                .description("something")
                .cost(100)
                .categoryId("23223")
                .availability(true)
                .date("10-29-2010")
                .build();
        venueRepository.createVenue(venue);
    }

    @Test
    void updateUserType() {
    }

    @Test
    void readAll() {
        System.out.println(venueRepository.readAllAvailable());
    }

    @Test
    void testReadAll() {
    }

    @Test
    void readAllAvailable() {
    }

    @Test
    void readAllUnAvailable() {
    }

    @Test
    void update() {
        Venue venue = Venue.builder()
                .id(103)
                .name("test")
                .maxNumGuest(12)
                .location("none")
                .description("something nice one")
                .cost(100)
                .categoryId("23223")
                .availability(true)
                .date("10-29-2010")
                .build();
        venueRepository.update(venue);
    }
}