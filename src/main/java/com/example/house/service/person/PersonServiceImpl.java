package com.example.house.service.person;

import com.example.house.dto.person.LogonPersonDto;
import com.example.house.dto.person.PersonRegisterDto;
import com.example.house.dto.mapper.PersonMapper;
import com.example.house.entity.Person;
import com.example.house.exception.person.PersonValidationException;
import com.example.house.repository.PersonRepository;
import com.example.house.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public LogonPersonDto signin(String username, String password) {
        validateString(username, "Username can not be empty.");
        validateString(password, "Password can not be empty.");


        Person person = findByUsername(username);
        if(person == null) {
            throw new PersonValidationException("The username " + username + " doesn't exists.");
        }
        try{
            LogonPersonDto dto = PersonMapper.INSTANCE.getLogonDto(person);
            dto.setToken(getToken(username, password, dto.getRole()));
            return dto;
        } catch (BadCredentialsException ex) {
            throw new PersonValidationException("Wrong password");
        }

    }

    @Override
    public String signup(PersonRegisterDto dto) {
        Person person = PersonMapper.INSTANCE.getEntityFromPersonRegisterDto(dto);

        if(findByUsername(person.getUsername()) != null) {
            throw new PersonValidationException("The username already exists.");
        }

        validateString(dto.getUsername(), "Username can not be empty.");
        validateString(dto.getUserPassword(), "Password can not be empty.");
        validateString(dto.getUserPasswordConfirmation(), "Password confirmation can not be empty.");
        validateString(dto.getFirstName(), "First name can not be empty.");
        validateString(dto.getLastName(), "Last name can not be empty.");
        validateString(dto.getRole(), "Role can not be empty.");

        if(!(validatePasswords(dto.getUserPassword(), dto.getUserPasswordConfirmation()))){
            throw new PersonValidationException("The passwords are not the same.");
        }

        person.setUserPassword(passwordEncoder.encode(dto.getUserPassword()));
        personRepository.save(person);

        return jwtTokenProvider.createToken(person.getUsername(), person.getRole());
    }

    private Person findByUsername(String username) {
        validateString(username, "Username can not be empty.");
        return personRepository.findByUsername(username);
    }

    private void validateString(String value, String errorMessage) {
        if(errorMessage == null || errorMessage.isEmpty()) {
            errorMessage = "validateString function is throwing an error without a providing error message";
        }

        if(value == null || value.isEmpty()) {
            throw new PersonValidationException(errorMessage);
        }
    }

    private boolean validatePasswords(String pass1, String pass2){
        return pass1.equals(pass2);
    }

    /**
     * Given the username, password and roles generates a JWT token.
     * @param username username of the Person
     * @param password password of the Person
     * @param role i.e ADMIN, VIEWER, EDITOR
     * @return the generated token
     */
    private String getToken(String username, String password, String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        Authentication auth = new UsernamePasswordAuthenticationToken(username, password, authorities);
        authenticationManager.authenticate(auth);

        return jwtTokenProvider.createToken(username, role);
    }
}
