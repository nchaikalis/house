package com.example.house.rest.person;

import com.example.house.dto.LogonPersonDto;
import com.example.house.dto.PersonAttemptToLoginDto;
import com.example.house.dto.PersonRegisterDto;
import com.example.house.exception.AssetSaveException;
import com.example.house.exception.AssetSearchException;
import com.example.house.exception.PersonSearchException;
import com.example.house.exception.PersonValidationException;
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
    public LogonPersonDto signin(@RequestBody PersonAttemptToLoginDto dto) {
        return service.signin(dto.getUsername(), dto.getUserPassword());
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody PersonRegisterDto user) {
        try{
            service.signup(user);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        catch (PersonValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
