package com.example.house.service.person;

import com.example.house.dto.person.LogonPersonDto;
import com.example.house.dto.person.PersonRegisterDto;

public interface PersonService {
    LogonPersonDto signin(String username, String password);
    String signup(PersonRegisterDto dto);
}
