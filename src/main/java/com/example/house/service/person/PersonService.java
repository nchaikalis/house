package com.example.house.service.person;

import com.example.house.dto.LogonPersonDto;
import com.example.house.entity.Person;

public interface PersonService {
    void save(Person person);

    LogonPersonDto signin(String username, String password);
    String signup(Person dto);
}
