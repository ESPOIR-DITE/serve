/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.repository.venue;

import com.mycompany.server.domain.venue.Venue;
import com.mycompany.server.domain.venue.VenueCategory;
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
public class VenueCategoryRepository {
    private final Connection connection = new Repository().getConn();
    private final Statement stmt;

    public VenueCategoryRepository() throws SQLException {
        stmt = connection.createStatement();
    }
    public boolean createVenueRepositoryTable() {
        String query ="CREATE TABLE VENUEGATEGORY("
              + "ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,"
              + "CATEGORY varchar(30),"
              + "DESCRIPTION varchar(225),"
              + "PRIMARY KEY (Id))";
        try {
            stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    
    public VenueCategory readVenue(String ID) throws SQLException {
        VenueCategory venue = null;
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM VENUECATEGORY where ID="+ID);
        while (resultSet.next()){
            venue = VenueCategory.builder()
                    .id(resultSet.getInt("ID"))
                    .category(resultSet.getString("CATEGORY"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .build();
        }
        return venue;
    }
    public void createVenue(VenueCategory venue) throws SQLException {
        String query ="insert into VENUEGATEGORY values("+venue.getId()+","+venue.getCategory()+","+venue.getDescription()+")";
        int reslt = stmt.executeUpdate(query);
        System.out.println("result: "+reslt);
    }
    public void UpdateUserType(VenueCategory venue) throws SQLException {
        String query ="UPDATE VENUEGATEGORY SET ID="+venue.getId()+", CATEGORY="+venue.getCategory()+",DESCRIPTION="+venue.getDescription()+")";
        System.out.println("result: "+query);
    }
    public List<VenueCategory> readAll() throws SQLException {
        List<VenueCategory> users = new ArrayList();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM VENUEGATEGORY");
        while (resultSet.next()){
            VenueCategory.builder()
                    .id(resultSet.getInt("ID"))
                    .category(resultSet.getString("CATEGORY"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .build();
        }
        return users;
    }
}
