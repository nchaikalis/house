package com.example.house.rest.person;

import com.example.house.dto.LogonPersonDto;
import com.example.house.dto.PersonAttemptToLoginDto;
import com.example.house.entity.Person;
import com.example.house.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class PersonController {

    @Autowired
    PersonService service;
    @PostMapping("/signin")
    public LogonPersonDto signin(@RequestBody PersonAttemptToLoginDto dto) {
        return service.signin(dto.getUsername(), dto.getUserPassword());
    }

    @PostMapping("/signup")
    public String signup(@RequestBody Person user) {
        return service.signup(user);
    }
}
