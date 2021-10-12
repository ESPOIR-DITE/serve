/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.domain.user;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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
public class Users implements Serializable {
    private String email;
    private String name;
    private String surname;
    private String date;
}
