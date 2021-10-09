/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.repository.venue;

import com.mycompany.server.domain.venue.Venue;
import com.mycompany.server.repository.Repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marilyn
 */
public class VenueRepositoryTable{
    private final Connection connection;
    private final Statement stmt;

    public VenueRepositoryTable() throws SQLException {
        this.connection = new  Repository().getConn();
        stmt = connection.createStatement();
    }
    public void createVenueRepositoryTable() throws SQLException {
        String query ="CREATE TABLE VENUE ("
              + "ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,"
              + "NAME varchar(30),"
              + "LOCATION varchar(30),"
              + "COST INT,"
              + "MAXNUMGUEST INT,"
              + "AVAILABILITY BOOLEAN,"
              + "DATE DATE,"
              + "CATEGORYID varchar(30),"
              + "PRIMARY KEY (Id))";
        stmt.execute(query);
    }
    
    public Venue readVenue(String ID) throws SQLException {
        Venue venue = null;
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM VENUE where ID="+ID);
        while (resultSet.next()){
            venue = Venue.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .location(resultSet.getString("LOCATION"))
                    .cost(resultSet.getDouble("COST"))
                    .maxNumGuest(resultSet.getInt("MAXNUMGUEST"))
                    .availability(resultSet.getBoolean("AVAILABILITY"))
                    .date(resultSet.getDate("DATE").toLocalDate())
                    .categoryId(resultSet.getString("CATEGORYID"))
                    .build();
        }
        return venue;
    }
    public void createVenue(Venue venue) throws SQLException {
        String query ="insert into VENUE values("+venue.getId()+","+venue.getName()+","+venue.getLocation()+","
                + ""+venue.getCost()+","+venue.getMaxNumGuest()+","+venue.isAvailability()+","+venue.getDate()+","
                + ""+venue.getCategoryId()+")";
        int reslt = stmt.executeUpdate(query);
        System.out.println("resutl: "+reslt);
    }
    public void UpdateUserType(Venue venue) throws SQLException {
        String query ="UPDATE VENUE SET ID="+venue.getId()+", NAME="+venue.getName()+",LOCATION="+venue.getLocation()+",COST="
                + ""+venue.getCost()+",MAXNUMGUEST="+venue.getMaxNumGuest()+",AVAILABILITY="+venue.isAvailability()+",DATE="+venue.getDate()+","
                + "CATEGORYID="+venue.getCategoryId()+")";
        System.out.println("restl: "+query);
    }
    public List<Venue> readAll() throws SQLException {
        List<Venue> users = new ArrayList();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM VENUE");
        while (resultSet.next()){
            Venue.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .location(resultSet.getString("LOCATION"))
                    .cost(resultSet.getDouble("COST"))
                    .maxNumGuest(resultSet.getInt("MAXNUMGUEST"))
                    .availability(resultSet.getBoolean("AVAILABILITY"))
                    .date(resultSet.getDate("DATE").toLocalDate())
                    .categoryId(resultSet.getString("CATEGORYID"))
                    .build();
        }
        return users;
    }
}
