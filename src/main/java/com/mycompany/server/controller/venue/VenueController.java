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
            case "read-available": return readAllAvailable();
            case "read-unavailable": return readAllUnAvailable();
            case "delete": return delete(serverToken);
            case "update-available": return updateAvailable(serverToken);
            case "update-unavailable": return updateUnavailable(serverToken);
        }
        return null;
    }

    public String read(ServerToken serverToken){
        String email = (String) serverToken.getValue();
        Venue venue = venueRepository.readVenue(email);
        return VenueFactory.getVenueFromObject(venue);
    }
    public String updateUnavailable(ServerToken serverToken){
        return venueRepository.updateUnavailable(serverToken.getValue())+"";
    }
    public String updateAvailable(ServerToken serverToken){
        return venueRepository.updateAvailable(serverToken.getValue())+"";
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
        System.out.println("from controller"+venue);
        return venueRepository.update(venue)+"";
    }
    public String delete(ServerToken serverToken){
        return venueRepository.delete(serverToken.getValue())+"";
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
    public String readAllAvailable(){
        String listobjec = null;
        List<Venue> usersList= venueRepository.readAllAvailable();
        ObjectMapper mapper = new ObjectMapper();
        try {
            listobjec = mapper.writeValueAsString(usersList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listobjec;
    }
    public String readAllUnAvailable(){
        String listobjec = null;
        List<Venue> usersList= venueRepository.readAllUnAvailable();
        ObjectMapper mapper = new ObjectMapper();
        try {
            listobjec = mapper.writeValueAsString(usersList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listobjec;
    }
}
