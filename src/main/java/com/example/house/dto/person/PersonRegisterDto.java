package com.example.house.dto.person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRegisterDto {
    private String firstName;
    private String lastName;
    private String username;
    private String userPassword;
    private String userPasswordConfirmation;
    private String role;

    public PersonRegisterDto() {
    }

    public PersonRegisterDto(String firstName, String lastName, String username, String userPassword, String userPasswordConfirmation, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.userPassword = userPassword;
        this.userPasswordConfirmation = userPasswordConfirmation;
        this.role = role;
    }
}
