/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.domain.user;

/**
 *
 * @author Marilyn
 */
import lombok.*;

import java.io.Serializable;

@Getter @ToString 
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCredentials implements Serializable {
    private String id;
    private String email;
    private String password;
    private Boolean active;
    private String creator;
    private String userTypeId;
}
