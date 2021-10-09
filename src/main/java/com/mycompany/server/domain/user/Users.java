/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server.domain.user;

import java.time.LocalDate;
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
public class Users {
    private String email;
    private String name;
    private String surname;
    private LocalDate date;

}
