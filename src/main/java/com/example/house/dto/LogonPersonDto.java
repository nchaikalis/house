package com.example.house.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogonPersonDto {
    private int personId;
    private String firstName;
    private String lastName;
    private String username;
    private String role;
    private String token;

    public LogonPersonDto() {
    }

    public LogonPersonDto(int personId, String firstName, String lastName, String username, String role, String token) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
        this.token = token;
    }
}
