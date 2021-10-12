package com.mycompany.server.controller.venue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.server.domain.ServerToken;
import com.mycompany.server.domain.user.Users;
import com.mycompany.server.domain.venue.Venue;
import com.mycompany.server.factory.user.UserFactory;
import com.mycompany.server.factory.venue.VenueFactory;
import com.mycompany.server.repository.venue.VenueRepository;

import java.sql.SQLException;
import java.util.List;

public class VenueController {
    private VenueRepository venueRepository;

    public VenueController() {
        try{
            this.venueRepository = new VenueRepository();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public String getVenue(ServerToken serverToken){
        switch (serverToken.getRequest()){
            case "read": return read(serverToken);
            case "update": return update(serverToken);
            case "create-table": return createTable(serverToken);
            case "create": return createVenue(serverToken);
            case "reads": return readAll();
        }
        return null;
    }

    public String read(ServerToken serverToken){
        String email = (String) serverToken.getValue();
        Venue venue = venueRepository.readVenue(email);
        return VenueFactory.getVenueFromObject(venue);
    }
    public String createTable(ServerToken serverToken){
        Users user = UserFactory.getUserFromValue(serverToken.getValue());
        return venueRepository.createVenueRepositoryTable()+"";
    }
    public String createVenue(ServerToken serverToken){
        Venue venue = VenueFactory.getVenueFromValue(serverToken.getValue());
        return venueRepository.createVenue(venue)+"";
    }
    public String update(ServerToken serverToken){
        Venue venue = VenueFactory.getVenueFromValue(serverToken.getValue());
        return venueRepository.createVenue(venue)+"";
    }
    public String readAll(){
        String listobjec = null;
        List<Venue> usersList= venueRepository.readAll();
        ObjectMapper mapper = new ObjectMapper();
        try {
            listobjec = mapper.writeValueAsString(usersList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listobjec;
    }
}
