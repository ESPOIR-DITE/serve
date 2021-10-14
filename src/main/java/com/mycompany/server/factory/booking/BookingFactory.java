package com.mycompany.server.factory.booking;

import com.mycompany.server.domain.Booking;
import com.mycompany.server.domain.customer.Customer;

import java.util.StringTokenizer;

public class BookingFactory {
    public static String getBookingFromObject(Booking booking){
        return booking.getId()+"/"+booking.getCustomerEmail()+"/"+booking.getUserEmail()+"/"+booking.getVenueId()+"/"+booking.getDate()+"/"+booking.getDescription();
    }
    public static Booking getBookingFromValue(String value){
        StringTokenizer st = new StringTokenizer(value,"/");
        return Booking.builder()
                .id(Integer.parseInt(st.nextToken()))
                .customerEmail(st.nextToken())
                .userEmail(st.nextToken())
                .venueId(st.nextToken())
                .date(st.nextToken())
                .description(st.nextToken())
                .build();
    }
}
