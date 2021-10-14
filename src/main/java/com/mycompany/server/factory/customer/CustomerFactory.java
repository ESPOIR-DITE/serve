package com.mycompany.server.factory.customer;

import com.mycompany.server.domain.customer.Customer;

import java.util.Date;
import java.util.StringTokenizer;

public class CustomerFactory {
    public static String getCustomerFromObject(Customer customer){
        return customer.getEmail()+"/"+customer.getName()+"/"+customer.getSurname()+"/"+customer.getDate();
    }
    public static Customer getCustomerFromValue(String value){
        StringTokenizer st = new StringTokenizer(value,"/");
        return Customer.builder()
                .email(st.nextToken())
                .name(st.nextToken())
                .surname(st.nextToken())
                .date(st.nextToken())
                .build();
    }
}
