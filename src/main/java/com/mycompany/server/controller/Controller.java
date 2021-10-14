/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.controller;

import com.mycompany.server.controller.customer.CustomerController;
import com.mycompany.server.controller.user.UserController;
import com.mycompany.server.controller.user.UserCredentialController;
import com.mycompany.server.controller.user.UserTypeController;
import com.mycompany.server.controller.venue.VenueController;
import com.mycompany.server.domain.ServerToken;
import com.mycompany.server.factory.ServerTokenFactory;

/**
 *
 * @author Marilyn
 */
public class Controller {
    private final UserController userController;
    private final UserCredentialController userCredentialController;
    private final VenueController venueController;
    private final BookingController bookingController;
    private final CustomerController customerController;

    public Controller() {
        this.userController = new UserController();
        this.userCredentialController = new UserCredentialController();
        this.venueController = new VenueController();
        this.bookingController = new BookingController();
        this.customerController = new CustomerController();
    }

    public String getServerToken(ServerToken serverToken){

        //System.out.println("response: "+serverToken.getResponse());

        switch (serverToken.getDomain()){
            case "user": return userController.getUser(serverToken);
            case "user-credential": return userCredentialController.getUserCredentials(serverToken);
            case "booking": return bookingController.getBooking(serverToken);
            case "venue": return venueController.getVenue(serverToken);
            case "customer": return customerController.getUser(serverToken);
        }
        return "ServerTokenFactory.makeWrongRequest()";
    }
}
