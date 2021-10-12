/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.domain;

import com.mycompany.server.domain.user.Users;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.*;

/**
 *
 * @author Marilyn
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ServerToken  implements Serializable{
    private Users users;
    private String requestType;
    private Date date;
    private String domain;
    private String request;
    private String value;
    public String getString(){
        return requestType+"/"+date+"/"+domain+"/"+request+"/"+value;
    }
}
