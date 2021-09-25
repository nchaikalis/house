package com.example.house.service.person;

import com.example.house.dto.LogonPersonDto;
import com.example.house.dto.mapper.PersonMapper;
import com.example.house.entity.Person;
import com.example.house.exception.PersonValidationException;
import com.example.house.repository.PersonRepository;
import com.example.house.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
    public void save(Person person) {

    }

    @Override
    public LogonPersonDto signin(String username, String password) {
        validateString(username, "Username can not be empty.");
        validateString(password, "Password can not be empty.");

        try {
            Person person = findByUsername(username);
            LogonPersonDto dto = PersonMapper.INSTANCE.getDto(person);
            dto.setToken(getToken(username, password, dto.getRole()));
            return dto;
        } catch (Exception e) {
            throw new PersonValidationException("Invalid username/password supplied");
        }
    }

    @Override
    public String signup(Person dto) {
        return null;
    }

    private Person findByUsername(String username) {
        validateString(username, "Username can not be empty.");
        Person person = personRepository.findByUsername(username);
        if(person == null) {
            throw new PersonValidationException("The username " + username + " doesn't exists.");
        }
        return person;
    }

    private void validateString(String value, String errorMessage) {
        if(errorMessage.isEmpty()) {
            errorMessage = "validateString function is throwing an error without a providing error message";
        }

        if(value == null || value.isEmpty()) {
            throw new PersonValidationException(errorMessage);
        }
    }

    /**
     * Given the username, password and roles generates a JWT token.
     * @param username
     * @param password
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
