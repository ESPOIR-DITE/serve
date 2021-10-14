/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.repository.venue;

import com.mycompany.server.domain.Booking;
import com.mycompany.server.domain.venue.Venue;
import com.mycompany.server.domain.venue.VenueCategory;
import com.mycompany.server.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marilyn
 */
public class VenueRepository {
    private final Connection connection = new Repository().getConn();
    private final Statement stmt;

    public VenueRepository() throws SQLException {
        stmt = connection.createStatement();
    }
    public boolean createVenueRepositoryTable() {
        String query ="CREATE TABLE VENUE ("
              + "Id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,"
              + "NAME varchar(30),"
              + "LOCATION varchar(30),"
              + "COST double ,"
              + "MAXNUMGUEST integer,"
              + "AVAILABILITY varchar(30),"
              + "DATE varchar(30),"
              + "DESCRIPTIONS varchar(30),"
              + "CATEGORYID varchar(30),"
              + "PRIMARY KEY (Id))";
        try {
            stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean delete(String id)  {
        try {
            stmt.executeUpdate("DELETE FROM VENUE where ID="+id+"");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public Venue readVenue(String ID)  {
        Venue venue = null;
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * FROM VENUE where ID="+ID);
        while (resultSet.next()){
            Venue.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .location(resultSet.getString("LOCATION"))
                    .cost(resultSet.getDouble("COST"))
                    .maxNumGuest(resultSet.getInt("MAXNUMGUEST"))
                    .availability(resultSet.getBoolean("AVAILABILITY"))
                    .date(resultSet.getString("DATE"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .categoryId(resultSet.getString("CATEGORYID"))
                    .build();
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return venue;
    }
    public boolean createVenue(Venue venue)  {
        try {
            String query = "INSERT INTO VENUE (NAME,LOCATION,COST,MAXNUMGUEST,AVAILABILITY,DATE,DESCRIPTIONS,CATEGORYID) VALUES ('"+venue.getName()+"','"+venue.getLocation()+"',"+venue.getCost()+","+venue.getMaxNumGuest()+",'"+venue.isAvailability()+"','"+venue.getDate()+"','"+venue.getDescription()+"','"+venue.getCategoryId()+"')";
            stmt.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
//    public void UpdateUserType(VenueCategory venue) throws SQLException {
//        String query ="UPDATE VENUEGATEGORY SET ID="+venue.getId()+", CATEGORY="+venue.getCategory()+",DESCRIPTION="+venue.getDescription()+")";
//        System.out.println("result: "+query);
//    }
    public List<Venue> readAll()  {
        List<Venue> venues = new ArrayList();
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * FROM VENUE");

        while (resultSet.next()){
            Venue venue = Venue.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .location(resultSet.getString("LOCATION"))
                    .cost(resultSet.getDouble("COST"))
                    .maxNumGuest(resultSet.getInt("MAXNUMGUEST"))
                    .availability(resultSet.getBoolean("AVAILABILITY"))
                    .date(resultSet.getString("DATE"))
                    .description(resultSet.getString("DESCRIPTIONS"))
                    .categoryId(resultSet.getString("CATEGORYID"))
                    .build();
            venues.add(venue);
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return venues;
    }
    public List<Venue> readAllAvailable()  {
        List<Venue> venues = new ArrayList();
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * FROM VENUE where AVAILABILITY='true'");

            while (resultSet.next()){
            Venue venue = Venue.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .location(resultSet.getString("LOCATION"))
                    .cost(resultSet.getDouble("COST"))
                    .maxNumGuest(resultSet.getInt("MAXNUMGUEST"))
                    .availability(resultSet.getBoolean("AVAILABILITY"))
                    .date(resultSet.getString("DATE"))
                    .description(resultSet.getString("DESCRIPTIONS"))
                    .categoryId(resultSet.getString("CATEGORYID"))
                    .build();
            venues.add(venue);
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return venues;
    }
    public List<Venue> readAllUnAvailable()  {
        List<Venue> venues = new ArrayList();
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * FROM VENUE where AVAILABILITY='false'");

            while (resultSet.next()){
            Venue venue = Venue.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .location(resultSet.getString("LOCATION"))
                    .cost(resultSet.getDouble("COST"))
                    .maxNumGuest(resultSet.getInt("MAXNUMGUEST"))
                    .availability(resultSet.getBoolean("AVAILABILITY"))
                    .date(resultSet.getString("DATE"))
                    .description(resultSet.getString("DESCRIPTIONS"))
                    .categoryId(resultSet.getString("CATEGORYID"))
                    .build();
            venues.add(venue);
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return venues;
    }
    public boolean update(Venue venue)  {
        String query = "UPDATE VENUE SET NAME=?,LOCATION=?,COST=?,MAXNUMGUEST=?,AVAILABILITY=?,DATE=?,DESCRIPTIONS=?,CATEGORYID=? WHERE ID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,venue.getName());
            ps.setString(2,venue.getLocation());
            ps.setDouble(3,venue.getCost());
            ps.setInt(4,venue.getMaxNumGuest());
            ps.setString(5,venue.isAvailability()+"");
            ps.setString(6,venue.getDate()+"");
            ps.setString(7,venue.getDescription());
            ps.setString(8,venue.getCategoryId());
            ps.setInt(9,venue.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean updateAvailable(String id)  {
        String query = "UPDATE VENUE SET AVAILABILITY=? WHERE ID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,"true");
            ps.setString(2,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean updateUnavailable(String id)  {
        String query = "UPDATE VENUE SET AVAILABILITY=? WHERE ID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,"false");
            ps.setString(2,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
}
