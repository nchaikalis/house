package com.example.house.dto.person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonAttemptToLoginDto {
    private String username;
    private String userPassword;

    public PersonAttemptToLoginDto() {
    }

    public PersonAttemptToLoginDto(String username, String userPassword) {
        this.username = username;
        this.userPassword = userPassword;
    }
}
