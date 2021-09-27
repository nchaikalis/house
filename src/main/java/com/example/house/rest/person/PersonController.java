package com.example.house.rest.person;

import com.example.house.dto.person.LogonPersonDto;
import com.example.house.dto.person.PersonAttemptToLoginDto;
import com.example.house.dto.person.PersonRegisterDto;
import com.example.house.exception.person.PersonException;
import com.example.house.exception.person.PersonValidationException;
import com.example.house.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> signin(@RequestBody PersonAttemptToLoginDto dto) {
        try{
            LogonPersonDto logonPersonDto = service.signin(dto.getUsername(), dto.getUserPassword());
            return new ResponseEntity<>(logonPersonDto, HttpStatus.OK);
        }
        catch (PersonException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody PersonRegisterDto user) {
        try{
            service.signup(user);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        catch (PersonException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
