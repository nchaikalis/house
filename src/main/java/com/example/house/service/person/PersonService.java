package com.example.house.service.person;

import com.example.house.dto.LogonPersonDto;
import com.example.house.dto.PersonRegisterDto;

public interface PersonService {
    LogonPersonDto signin(String username, String password);
    String signup(PersonRegisterDto dto);
}
