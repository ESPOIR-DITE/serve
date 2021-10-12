package com.mycompany.server.factory.venue;

import com.mycompany.server.domain.user.Users;
import com.mycompany.server.domain.venue.Venue;

import java.util.StringTokenizer;

public class VenueFactory {
    public static String getVenueFromObject(Venue venue){
        return venue.getId()+"/"+ venue.getName()+"/"+ venue.getLocation()+"/"+ venue.getCost()+"/"+ venue.getMaxNumGuest()+"/"+ venue.isAvailability()+"/"+ venue.getDate()+"/"+ venue.getDescription()+"/"+ venue.getCategoryId();
    }
    public static Venue getVenueFromValue(String value){
        System.out.println(value);
        StringTokenizer st = new StringTokenizer(value,"/");
        st.nextToken();
        return Venue.builder()
                .name(st.nextToken())
                .location(st.nextToken())
                .cost(Double.parseDouble(st.nextToken())) // for now.
                .maxNumGuest(Integer.parseInt(st.nextToken())) // for now.
                .date(st.nextToken())
                .description(st.nextToken())
                .categoryId(st.nextToken())
                .build();
    }
}
