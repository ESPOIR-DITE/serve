package com.mycompany.server.repository.booking;

import com.mycompany.server.domain.Booking;
import com.mycompany.server.domain.customer.Customer;
import com.mycompany.server.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private final Connection connection = new Repository().getConn();
    private Statement stmt;

    public BookingRepository() throws SQLException {
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
            String query = "CREATE TABLE BOOKING (" +
                    "Id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY," +
                    "CUSTOMEREMAIL varchar(30)," +
                    "USEREMAIL varchar(30)," +
                    "VENUEID varchar(30)," +
                    "DATE varchar(30)," +
                    "DESCRIPTION varchar(30)," +
                    "PRIMARY KEY (Id))";
            resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public Booking read(String id)  {
        Booking booking = null;
        try {
         ResultSet  resultSet = stmt.executeQuery("SELECT * FROM BOOKING where ID="+id+"");
            while (resultSet.next()){
                booking = Booking.builder()
                        .id(resultSet.getInt("ID"))
                        .customerEmail(resultSet.getString("CUSTOMEREMAIL"))
                        .userEmail(resultSet.getString("USEREMAIL"))
                        .venueId(resultSet.getString("VENUEID"))
                        .date(resultSet.getString("DATE"))
                        .description(resultSet.getString("DESCRIPTION"))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return booking;
    }
    public boolean createBooking(Booking booking)  {
        try {
            String query = "INSERT INTO BOOKING (CUSTOMEREMAIL,USEREMAIL,VENUEID,DATE) VALUES('"+booking.getCustomerEmail()+"','"+booking.getUserEmail()+"','"+booking.getVenueId()+"','"+booking.getDate()+"')";
            stmt.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean Update(Booking booking)  {
        String query = "UPDATE BOOKING SET CUSTOMEREMAIL=?,USEREMAIL=?,VENUEID=?,DATE=?,DESCRIPTION=? WHERE ID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,booking.getCustomerEmail());
            ps.setString(2,booking.getUserEmail());
            ps.setString(3,booking.getVenueId());
            ps.setString(4,booking.getDate());
            ps.setString(5,booking.getDescription());
            ps.setInt(6,booking.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean delete(String id)  {
        try {
            stmt.executeUpdate("DELETE FROM BOOKING where ID="+id+"");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public List<Booking> readAll() {
        List<Booking> bookings = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("SELECT * FROM BOOKING ");
            while (resultSet.next()){
                bookings.add(Booking.builder()
                        .id(resultSet.getInt("id"))
                        .customerEmail(resultSet.getString("CUSTOMEREMAIL"))
                        .userEmail(resultSet.getString("USEREMAIL"))
                        .venueId(resultSet.getString("VENUEID"))
                        .date(resultSet.getString("DATE"))
                        .description(resultSet.getString("DESCRIPTION"))
                        .build());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return bookings;
    }
}
